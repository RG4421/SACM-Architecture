//package misc
//
//import groovy.json.JsonOutput
//import groovy.json.JsonParserType
//import groovy.json.JsonSlurper
//import net.sf.saxon.TransformerFactoryImpl
//
//import javax.xml.transform.TransformerConfigurationException
//import javax.xml.transform.TransformerException
//import javax.xml.transform.TransformerFactory
//import javax.xml.transform.stream.StreamResult
//import javax.xml.transform.stream.StreamSource
//
//def p   = "C:\\CIS-CAT-Pro-Assessor-v4\\Assessor-CLI\\reports"
//def ifn = "${p}\\CIS-CAT-DEV-CIS_Microsoft_Windows_10_Enterprise_Release_1607_Benchmark-ARF-20190123T121302Z.xml"
//def ofn = "${p}\\out.json"
//def ofnp = "${p}\\out-pretty.json"
//
//def input  = new FileReader(new File(ifn))
//def outfile = new File(ofn)
//def output = new FileOutputStream(outfile)
//
//def rsrc = "xml-to-json.xsl"
//def xslt = this.class.classLoader.getResource(rsrc)
//if (xslt) {
//	try {
//		def tfact = (TransformerFactoryImpl)TransformerFactory.newInstance()
//		def tfmr = tfact.newTransformer(new StreamSource(xslt.toString()))
//
//		tfmr.transform(new StreamSource(input), new StreamResult(output))
//
//		new File(ofnp).withWriter { w ->
//			w.write(JsonOutput.prettyPrint(outfile.text))
//		}
//
//	} catch (TransformerConfigurationException tce) {
//		println tce.localizedMessage
//	} catch (TransformerException te) {
//		println te.localizedMessage
//	}
//} else {
//	println "Error loading XSLT resource (${rsrc})."
//}