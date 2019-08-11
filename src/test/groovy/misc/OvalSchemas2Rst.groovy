package misc

import net.sf.saxon.TransformerFactoryImpl

import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

def baseDir   = "C:\\_Development\\Projects\\OVAL\\MAIN-OVAL-Community\\OVAL"
def schemaDir = "${baseDir}\\oval-schemas"
def outputDir = "${baseDir}\\guidelines\\oval-schema-documentation"

def rsrc = "oval_xsd2rst.xsl"
def xslt = this.class.classLoader.getResource(rsrc)
if (xslt) {
	def tfact = (TransformerFactoryImpl)TransformerFactory.newInstance()
	def tfmr = tfact.newTransformer(new StreamSource(xslt.toString()))

	new File(schemaDir).listFiles().each { f ->
		def xsd = new FileReader(f)
		def rst = new FileOutputStream("${outputDir}\\${f.name[0..(f.name.indexOf(".xsd")-1)]}.rst")
		tfmr.transform(new StreamSource(xsd), new StreamResult(rst))
	}
}