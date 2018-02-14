$files = get-childitem -filter *.p*m

foreach ($file in $files)
{
	$newFile = $file.name + ".jpg"
	cmd /c .\convert.exe $file.name $newFile
}