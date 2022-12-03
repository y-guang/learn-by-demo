$list = @("Spike", "Pig", "Cat", "Cat")

# foreach o
$list | ForEach-Object {"Hi $_"} # use $_ for current element

# where
$list | Where-Object {$_ -match 'i'}

# select
Write-Output("first")
$list | Select-Object -First 2
Write-Output("last")
$list | Select-Object -Last 2
Write-Host("skip")
$list | Select-Object -Skip 2
Write-Host("unique")
$list | Select-Object -Unique
Get-ComputerInfo | Select-Object -ExpandProperty OsName # return only value
Get-ComputerInfo | Select-Object -Property OsName # return a key-value

