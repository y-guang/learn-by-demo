# predefined varibles
$null
$true
$false
$_ 

# list
$list = @(1, 2, "three") # different variable types are allowed
Write-Output("the list is:")
$list
Write-Output("0th element:")
$list[0] # index start at 0

#hash table
$hashtable = @{'Spike' = 15; 1 = 'c' # not allow "1" = 32 
2 = 3} # new line work as ;
Write-Output("the table is:")
$hashtable
Write-Output("elements:")
$hashtable['Spike']
$hashtable[1]
Write-Output("try string")
$hashtable["1"] # even we cannot add "1" = 32, the key 1 != "1"
Write-Output("index by dot")
$hashtable.2