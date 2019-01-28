package br.com.controle.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
//import javax.inject.Inject;
//import javax.servlet.http.HttpSession;

public class Autorizador implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		System.out.println("afterPhase(PhaseEvent evento)");
		FacesContext context = evento.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println("Nome da página: " + nomePagina);
		if (nomePagina.endsWith("/view/index.xhtml") ||
			nomePagina.endsWith("/view/lancamento.xhtml") || 
			nomePagina.endsWith("/view/sankhya.xhtml") ||
		nomePagina.endsWith("/view/lancamentos.xhtml")){
			return;
		}
		
		
		// Redirecionamento para index.xhtml
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "/view/index?faces-redirect=true");
		context.renderResponse();
	} 

	@Override
	public void beforePhase(PhaseEvent evento) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
