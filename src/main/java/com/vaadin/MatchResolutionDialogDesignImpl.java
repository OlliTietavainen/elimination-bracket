package com.vaadin;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Validator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.model.Match;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class MatchResolutionDialogDesignImpl extends MatchResolutionDialogDesign {

	Validator val = new Validator() {

		@Override
		public void validate(Object value) throws InvalidValueException {
			String stringValue = (String) value;
			if ("".equals(stringValue)) {
				return;
			}
			try {
				int intValue = Integer.parseInt(stringValue);
				if (intValue < 0) {
					throw new InvalidValueException("Score can't be negative!");
				}
			} catch (NumberFormatException nfe) {
				throw new InvalidValueException("Must be zero or a positive integer");
			}
		}
		
	};
	
	private ValueChangeListener list = new ValueChangeListener() {
		
		@Override
		public void valueChange(ValueChangeEvent event) {
			int p1 = getTextFieldInt(player1TextField);
			int p2 = getTextFieldInt(player2TextField);
			if ((p1 > 0 || p2 > 0) && (p1 - p2 != 0)) {
				confirmButton.setEnabled(true);
			} else {
				confirmButton.setEnabled(false);
			}
		}
	};
	
	public MatchResolutionDialogDesignImpl(Window window, Match match) {
		cancelButton.addClickListener(event -> window.close());
		confirmButton.addClickListener(event -> {
			int p1 = getTextFieldInt(player1TextField);
			int p2 = getTextFieldInt(player2TextField);
			match.resolve(p1, p2);
			window.close();
		});
		player1TextField.addValidator(val);
		player2TextField.addValidator(val);
		player1TextField.addValueChangeListener(list);
		player2TextField.addValueChangeListener(list);
	}
	
	private int getTextFieldInt(TextField tf) {
		String value = tf.getValue();
		if ("".equals(value)) {
			return -1;
		}
		return Integer.parseInt(value);
	}
	
}
