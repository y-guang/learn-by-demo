# if else
Write-Output("if else")
if ($true) {
    Write-Output("if $true")
} elseif ($true) {
    Write-Output("elif")
} else {
    Write-Output("cannot go here")
}

# switch
Write-Output("switch")
$case = 1
switch ($case) {
    0 { 
        Write-Output("case 0") 
    }
    1 { 
        Write-Output("case 1")
    }
    1 {
        Write-Output("case 1 another check and break")
        break
        # switch alway gothrough all case check until break
    }
    1 {
        # because break, should not go here
        Write-Output("should not go here")
    }
    Default {
        Write-Output("case default")
    }
}

# for
Write-Output("for loop")
for ($i = 0; $i -lt 2; $i++) {
    Write-Output("the i is $i")
}

# forEach
Write-Output("foreach")
$list = @(2, 4)
foreach ($j in $list) {
    Write-Output("the j is $j")
}

# while loop
Write-Output("while loop")
$i = 1
while ($i -lt 3) {
    Write-Output("the i is $i")
    $i = $i + 1
}

# do while / until
Write-Output("do until")
$i = 1
do {
    Write-Output("the i is $i")
    $i = $i + 1
} until (
    $i -gt 2
)