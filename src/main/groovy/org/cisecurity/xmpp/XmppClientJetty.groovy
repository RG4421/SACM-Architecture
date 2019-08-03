package org.cisecurity.xmpp

import org.eclipse.jetty.server.Handler
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.server.handler.DefaultHandler
import org.eclipse.jetty.server.handler.HandlerList
import org.eclipse.jetty.server.handler.ResourceHandler
import org.eclipse.jetty.servlet.ServletHandler
import org.slf4j.LoggerFactory

import javax.servlet.ServletException
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class XmppClientJetty {
	def log = LoggerFactory.getLogger(XmppClientJetty.class)

	static void main(String[] args) {
		new XmppClientJetty().start()
	}

	def ucf = { fm ->
		log.info "[UBUNTU CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}

	/**
	 * Start Jetty.
	 */
	void start() {
		log.info "User.Home --> ${System.properties['user.dir']}"
		Server server = new Server(2222)

//		ServletHandler handler = new ServletHandler()
//		server.handler = handler
//
//		handler.addServletWithMapping(HeartbeatServlet.class, "/*")
		ResourceHandler resourceHandler = new ResourceHandler()
		resourceHandler.directoriesListed = true
		resourceHandler.welcomeFiles = ["index.html"]
		resourceHandler.resourceBase = "${System.properties['user.dir']}${File.separator}html"

		HandlerList handlers = new HandlerList()
		def b = [resourceHandler, new DefaultHandler()] as Handler[]
		handlers.setHandlers(b)

		server.setHandler(handlers)

		// Connect the XMPP client
//		XmppProxy collector = new XmppProxy(callback: ucf, xmppDomain: "ip-0a1e0af4")
//		collector.connect(new User(username: "ubuntu", password: "Pt3ttcs2h!", resource: "resource"))

		try {
			server.start()
//			server.dumpStdErr()
			server.join()
		} finally {
			log.info "Finally"
			log.info "Disconnecting Collector"
//			collector.close()

			log.info "[FN] Disconnecting Jetty"
			server.destroy()
		}
	}
}

class HeartbeatServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html")
		response.setStatus(HttpServletResponse.SC_OK)
		response.writer.println "<h2>Success</h2>"
	}
}
