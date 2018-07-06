package org.vepo.vcws.ui;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

import org.vepo.vcws.model.Customer;
import org.vepo.vcws.model.CustomerRepository;

/**
 * Edit Customer View
 * 
 * @author Victor Osório
 */
@CDIView(EditCustomerView.EDIT_CUSTOMER_VIEW)
public class EditCustomerView extends GridLayout implements View {

    private static final long serialVersionUID = 4826368068952155095L;

    public static final String EDIT_CUSTOMER_VIEW = "edit";

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private javax.enterprise.event.Event<NavigateEvent> navigateEvent;

    private Customer customer;

    public EditCustomerView() {
        super(/* columns */ 2, /* rows */ 3);
        setWidth(100, Unit.PERCENTAGE);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        Optional<Customer> customer = customerRepository.findById(Long.valueOf(event.getParameters()));
        if (customer.isPresent()) {
            this.customer = customer.get();
            binder.setBean(this.customer);
        } else {
            Notification.show("Error!", "Could not find Customer with id!", Type.ERROR_MESSAGE);
            navigateEvent.fire(new NavigateEvent(ListCustomerView.LIST_CUSTOMER_VIEW));
        }
    }

    private Binder<Customer> binder = new Binder<>(Customer.class);

    @PostConstruct
    private void setup() {
        TextField firstNameField = new TextField("First Name");
        TextField lastNameField = new TextField("Last Name");
        Button btnSave = new Button("Save");

        binder.forField(firstNameField).asRequired().bind(Customer::getFirstName, Customer::setFirstName);
        binder.forField(lastNameField).asRequired().bind(Customer::getLastName, Customer::setLastName);

        btnSave.addClickListener(this::save);

        addComponent(new Label("Edit Customer"), 0, 0, 1, 0);
        addComponent(firstNameField);
        addComponent(lastNameField);
        addComponent(getButtons(), 0, 2, 1, 2);
    }

    private Component getButtons() {
        HorizontalLayout buttonsBar = new HorizontalLayout();
        buttonsBar.setWidth(100, Unit.PERCENTAGE);

        HorizontalLayout inner = new HorizontalLayout();

        Button btnSave = new Button("Save");
        btnSave.addClickListener(this::save);
        inner.addComponent(btnSave);

        buttonsBar.addComponent(inner);
        buttonsBar.setComponentAlignment(inner, Alignment.MIDDLE_RIGHT);
        return buttonsBar;
    }

    private void save(ClickEvent event) {
        try {
            binder.writeBean(this.customer);
            customerRepository.save(this.customer);
            Notification.show("Success", "The customer changes was successfully saved!", Type.HUMANIZED_MESSAGE);
            navigateEvent.fire(new NavigateEvent(ListCustomerView.LIST_CUSTOMER_VIEW));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

}