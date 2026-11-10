param(
    [int[]] $Sizes = @(1000, 2000, 3000, 4000, 5000, 6000),
    [int] $Repeats = 5
)

$ErrorActionPreference = 'Stop'
$ProjectRoot = (Resolve-Path (Join-Path $PSScriptRoot '..')).Path
Set-Location $ProjectRoot

$BundledMaven = Join-Path $ProjectRoot '.tools\maven-extract\apache-maven-3.10.0\bin\mvn.cmd'
$MavenCommand = Get-Command mvn.cmd -ErrorAction SilentlyContinue
if ($MavenCommand) {
    $Maven = $MavenCommand.Source
} elseif (Test-Path -LiteralPath $BundledMaven) {
    $Maven = $BundledMaven
} else {
    throw 'Maven was not found. Install Maven 3.x and add mvn.cmd to PATH.'
}
$OutputDirectory = Join-Path $PSScriptRoot 'results'
New-Item -ItemType Directory -Force -Path $OutputDirectory | Out-Null
$Stamp = Get-Date -Format 'yyyyMMdd-HHmmss'
$CsvPath = Join-Path $OutputDirectory "sla-deralba-gocj-$Stamp.csv"
$Rows = [System.Collections.Generic.List[object]]::new()

'"record_type","dataset","jobs","repetition","makespan","arur","throughput","energy_joules","execution_time","cost_units","jrr"' |
    Set-Content -LiteralPath $CsvPath -Encoding UTF8

foreach ($Size in $Sizes) {
    $Dataset = "GoCJ_v1\Enhanced_GoCJ\GoCJ_Dataset_$Size.csv"
    if (-not (Test-Path -LiteralPath $Dataset)) {
        throw "GoCJ dataset not found: $Dataset"
    }

    for ($Rep = 1; $Rep -le $Repeats; $Rep++) {
        Write-Host "Running GoCJ $Size jobs, repeat $Rep of $Repeats..."
        $WorkbookPath = "dynamic_sla-de-ralba\results\gocj-$Stamp-$Size-r$Rep.xlsx"
        $ExecArgs = "-Dexec.args=$Dataset $WorkbookPath"
        $RunOutput = & $Maven -q "-Dmaven.repo.local=$ProjectRoot\.m2\repository" `
            -f 'dynamic_sla-de-ralba\pom.xml' compile `
            'org.codehaus.mojo:exec-maven-plugin:3.5.0:java' $ExecArgs 2>&1
        if ($LASTEXITCODE -ne 0) {
            $RunLog = Join-Path $OutputDirectory "sla-deralba-gocj-$Stamp-failed-$Size-$Rep.log"
            $RunOutput | Set-Content -LiteralPath $RunLog -Encoding UTF8
            throw "Run failed for $Size jobs, repeat $Rep. Details: $RunLog"
        }

        $MetricLine = $RunOutput | ForEach-Object { $_.ToString() } |
            Where-Object { $_.StartsWith('METRICS,') } | Select-Object -Last 1
        if (-not $MetricLine) {
            $RunLog = Join-Path $OutputDirectory "sla-deralba-gocj-$Stamp-missing-metrics-$Size-$Rep.log"
            $RunOutput | Set-Content -LiteralPath $RunLog -Encoding UTF8
            throw "Metrics line missing for $Size jobs, repeat $Rep. Details: $RunLog"
        }

        $Metric = $MetricLine.Split(',')
        $Rows.Add([pscustomobject]@{
            record_type = 'run'
            dataset = $Metric[1]
            jobs = $Size
            repetition = $Rep
            makespan = [double]::Parse($Metric[2], [Globalization.CultureInfo]::InvariantCulture)
            arur = [double]::Parse($Metric[3], [Globalization.CultureInfo]::InvariantCulture)
            throughput = [double]::Parse($Metric[4], [Globalization.CultureInfo]::InvariantCulture)
            energy_joules = [double]::Parse($Metric[5], [Globalization.CultureInfo]::InvariantCulture)
            execution_time = [double]::Parse($Metric[6], [Globalization.CultureInfo]::InvariantCulture)
            cost_units = [double]::Parse($Metric[7], [Globalization.CultureInfo]::InvariantCulture)
            jrr = [double]::Parse($Metric[8], [Globalization.CultureInfo]::InvariantCulture)
        })
        $Rows | Export-Csv -LiteralPath $CsvPath -NoTypeInformation -Append -Encoding UTF8
        $Rows.Clear()
        Write-Host "Completed GoCJ $Size jobs, repeat $Rep."
    }
}

$RunRows = Import-Csv -LiteralPath $CsvPath
foreach ($Group in ($RunRows | Group-Object jobs)) {
    $Samples = @($Group.Group)
    $Rows.Add([pscustomobject]@{
        record_type = 'mean'
        dataset = $Samples[0].dataset
        jobs = $Group.Name
        repetition = $Samples.Count
        makespan = ($Samples | Measure-Object -Property makespan -Average).Average
        arur = ($Samples | Measure-Object -Property arur -Average).Average
        throughput = ($Samples | Measure-Object -Property throughput -Average).Average
        energy_joules = ($Samples | Measure-Object -Property energy_joules -Average).Average
        execution_time = ($Samples | Measure-Object -Property execution_time -Average).Average
        cost_units = ($Samples | Measure-Object -Property cost_units -Average).Average
        jrr = ($Samples | Measure-Object -Property jrr -Average).Average
    })
}
if ($Rows.Count -gt 0) {
    $Rows | Export-Csv -LiteralPath $CsvPath -NoTypeInformation -Append -Encoding UTF8
}

Write-Host "CSV log: $CsvPath"
Write-Host "Per-run Excel workbooks: dynamic_sla-de-ralba\results\gocj-$Stamp-<jobs>-r<repeat>.xlsx"


