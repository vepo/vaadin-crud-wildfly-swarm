package org.vepo.vcws.ui;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("valo")
@CDIUI("")
@VaadinServletConfiguration(productionMode = false, ui = VaadinCrudUI.class)
public class VaadinCrudUI extends UI {

    private static final long serialVersionUID = 5336462976732559722L;

    @Inject
    private CDIViewProvider viewProvider;

    private Navigator navigator;

    public void navigatesTo(@Observes NavigateEvent navigates) {
        navigator.navigateTo(navigates.getPath());
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        navigator = new Navigator(this, this);
        navigator.addProvider(viewProvider);

        // Navigate to start view
        navigator.navigateTo(ListCustomerView.LIST_CUSTOMER_VIEW);
    }
}