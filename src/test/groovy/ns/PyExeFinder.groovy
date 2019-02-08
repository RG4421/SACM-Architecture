package ns

def p = "C:\\_Development\\Projects\\Assessor-Shared\\python\\py"
new File(p).listFiles(new FilenameFilter() {
	@Override
	boolean accept(File dir, String name) {
		return (name.endsWith(".exe"))
	}
}).each { f -> println f.name }