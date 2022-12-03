# object
Get-Location # result is an object
$location_obj = Get-Location
Write-Output("the path is")
$location_obj.Path

# get member can get all member of an obj
Write-Output("get member")
$location_obj | gm

# can use all .NET objs
$version = New-Object System.Version
$version
$version = New-Object System.Version 3, 6
$version
$version = [System.Version]::new()
$version
$version = [System.Version]::new(3, 7)
$version.ToString()

# type conversion
[int] 1.7
[System.Version] "2.3.1.7"

# class after v5.0
class Dog {
    [int]$age
    [int]$weight
    Dog() {
        $this.age, $this.weight = 0
    }
    Dog($age, $weight) {
        $this.age = $age
        $this.weight = $weight
    }
    [String]ToString() {
        return ($this.age, $this.weight) -join "."
    }
}
$dog = New-Object Dog
$dog
$dog = New-Object Dog 1, 2
$dog
$dog.age

enum DogType {
    bigDog = 0
    smallDog = 1
}

[DogType]::smallDog