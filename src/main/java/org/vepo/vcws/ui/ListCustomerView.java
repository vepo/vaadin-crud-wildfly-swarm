package org.vepo.vcws.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.vepo.vcws.model.Customer;
import org.vepo.vcws.model.CustomerRepository;

@CDIView(ListCustomerView.LIST_CUSTOMER_VIEW)
public class ListCustomerView extends VerticalLayout implements View {

	private static final long serialVersionUID = -5605952007036861639L;

	public static final String LIST_CUSTOMER_VIEW = "";

	@Inject
	private CustomerRepository customerRepository;

	private Grid<Customer> grid;

	@Inject
	private javax.enterprise.event.Event<NavigateEvent> navigateEvent;

	public ListCustomerView() {
		setWidth(100, Unit.PERCENTAGE);
	}

	private void newCostumer(ClickEvent event) {
		navigateEvent.fire(new NavigateEvent(NewCustomerView.NEW_CUSTOMER_VIEW));
	}

	@PostConstruct
	private void setup() {
		addComponent(getTile());
		addComponent(getButtons());

		grid = new Grid<>();
		grid.setWidth(100, Unit.PERCENTAGE);
		grid.addColumn(Customer::getFirstName).setCaption("First Name");
		grid.addColumn(Customer::getLastName).setCaption("Last Name");
		grid.addComponentColumn(customer -> {
			Button btnEdit = new Button(VaadinIcons.EDIT);
			btnEdit.setStyleName(ValoTheme.BUTTON_QUIET);
			btnEdit.addClickListener((evnt) -> navigateEvent
					.fire(new NavigateEvent(EditCustomerView.EDIT_CUSTOMER_VIEW + "/" + customer.getId())));
			return btnEdit;
		}).setCaption("Actions");
		addComponent(grid);
		listCustomers();
	}

	private Component getButtons() {
		HorizontalLayout panel = new HorizontalLayout();
		panel.setWidth(100, Unit.PERCENTAGE);
		HorizontalLayout bar = new HorizontalLayout();
		Button btnNew = new Button(VaadinIcons.PLUS, this::newCostumer);
		btnNew.setStyleName(ValoTheme.BUTTON_QUIET);
		bar.addComponent(btnNew);
		panel.addComponent(bar);
		panel.setComponentAlignment(bar, Alignment.MIDDLE_RIGHT);
		return panel;
	}

	private Component getTile() {
		Label title = new Label("Customers");
		title.addStyleName(ValoTheme.LABEL_H1);
		return title;
	}

	private void listCustomers() {
		grid.setItems(customerRepository.findAll());
	}
}