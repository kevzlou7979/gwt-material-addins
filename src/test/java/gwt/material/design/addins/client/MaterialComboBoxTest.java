/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.addins.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.base.MaterialAddinsTest;
import gwt.material.design.addins.client.base.constants.AddinsCssName;
import gwt.material.design.addins.client.combobox.MaterialComboBox;
import gwt.material.design.addins.client.combobox.events.RemoveItemEvent;
import gwt.material.design.addins.client.dto.SampleModel;
import gwt.material.design.addins.client.dto.User;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.html.Label;
import gwt.material.design.client.ui.html.Option;

/**
 * Test case for combobox component
 *
 * @author kevzlou7979
 */
public class MaterialComboBoxTest extends MaterialAddinsTest {

	public void init() {
		final MaterialComboBox<User> comboBox = new MaterialComboBox<>();
		checkWidget(comboBox);
		checkEvents(comboBox);
		checkProperties(comboBox);
		checkMultiSelection();
	}

	protected void checkAddItemOption() {
		final MaterialComboBox<String> comboBox = new MaterialComboBox<>();
		RootPanel.get().add(comboBox);
		// Check Initial children
		assertEquals(comboBox.getValues().size(), 0);
		assertEquals(comboBox.getChildren().size(), 3);
		// Check simple String object
		for (int i = 1; i <= 5; i++) {
			comboBox.addItem("item" + i);
		}
		assertEquals(comboBox.getValues().size(), 5);
		// Check ListBox
		assertNotNull(comboBox.getWidget(0));
		assertTrue(comboBox.getWidget(0) instanceof MaterialWidget);
		assertEquals(comboBox.getListbox(), comboBox.getWidget(0));
		final MaterialWidget listBox = comboBox.getListbox();
		assertEquals(listBox.getWidgetCount(), 5);
		for (final Widget w : listBox) {
			assertNotNull(w);
			assertTrue(w instanceof Option);
		}
		// Check Label
		assertNotNull(comboBox.getWidget(1));
		assertTrue(comboBox.getWidget(1) instanceof Label);
		final Label lblTitle = (Label) comboBox.getWidget(1);
		assertTrue(lblTitle.getElement().hasClassName(AddinsCssName.SELECT2LABEL));
		// Check Error Label
		assertNotNull(comboBox.getWidget(2));
		assertTrue(comboBox.getWidget(2) instanceof MaterialLabel);
	}

	@Override
	protected <T extends MaterialWidget> void checkChildren(final T widget) {
		checkAddItemOption();
	}

	@Override
	protected <T extends MaterialWidget & HasEnabled> void checkEnabled(final T widget) {
		final MaterialComboBox<User> comboBox = new MaterialComboBox<>();
		super.checkEnabled(comboBox, comboBox.getListbox());
	}

	protected <T extends MaterialComboBox> void checkEvents(final T comboBox) {
		comboBox.setEnabled(true);
		// Open Handler
		checkOpenHandler(comboBox);
		// Close Handler
		checkCloseHandler(comboBox);
		// Remove Item Handler
		final boolean[] isRemoveItemEvent = { false };
		comboBox.addRemoveItemHandler(event -> {
			isRemoveItemEvent[0] = true;
		});
		comboBox.fireEvent(new GwtEvent<RemoveItemEvent.RemoveItemHandler<?>>() {
			@Override
			public Type<RemoveItemEvent.RemoveItemHandler<?>> getAssociatedType() {
				return RemoveItemEvent.getType();
			}

			@Override
			protected void dispatch(final RemoveItemEvent.RemoveItemHandler<?> eventHandler) {
				eventHandler.onRemoveItem(null);
			}
		});
		assertTrue(isRemoveItemEvent[0]);
		// Selection Handler
		final boolean[] isSelectionEvent = { false };
		comboBox.addSelectionHandler(event -> {
			isSelectionEvent[0] = true;
		});
		comboBox.fireEvent(new GwtEvent<SelectionHandler<?>>() {
			@Override
			public Type<SelectionHandler<?>> getAssociatedType() {
				return SelectionEvent.getType();
			}

			@Override
			protected void dispatch(final SelectionHandler<?> eventHandler) {
				eventHandler.onSelection(null);
			}
		});
		assertTrue(isSelectionEvent[0]);
		// Value Change Handler
		final boolean[] isValueChangeEvent = { false };
		comboBox.addValueChangeHandler(event -> {
			isValueChangeEvent[0] = true;
		});
		comboBox.fireEvent(new GwtEvent<ValueChangeHandler<?>>() {
			@Override
			public Type<ValueChangeHandler<?>> getAssociatedType() {
				return ValueChangeEvent.getType();
			}

			@Override
			protected void dispatch(final ValueChangeHandler<?> eventHandler) {
				eventHandler.onValueChange(null);
			}
		});
		assertTrue(isValueChangeEvent[0]);
	}

	@Override
	protected <T extends MaterialWidget> void checkInteractionEvents(final T widget, final boolean enabled) {
		checkEvents(new MaterialComboBox());
	}

	protected <T extends MaterialComboBox<User>> void checkProperties(final T comboBox) {
		comboBox.setReadOnly(true);
		assertTrue(comboBox.isReadOnly());
		comboBox.setReadOnly(false);
		assertFalse(comboBox.isReadOnly());
		comboBox.setMultiple(true);
		assertTrue(comboBox.isMultiple());
		comboBox.setMultiple(false);
		assertFalse(comboBox.isMultiple());
		comboBox.setToggleReadOnly(true);
		assertTrue(comboBox.isToggleReadOnly());
		comboBox.setToggleReadOnly(false);
		assertFalse(comboBox.isToggleReadOnly());
		final List<User> users = new ArrayList<>();
		users.add(new User());
		comboBox.setAcceptableValues(users);
		comboBox.setHideSearch(true);
		assertTrue(comboBox.isHideSearch());
		comboBox.setLimit(10);
		assertEquals(comboBox.getLimit(), 10);
		comboBox.setLabel("label");
		assertEquals(comboBox.getLabel().getText(), "label");
		comboBox.setPlaceholder("placeholder");
		assertEquals(comboBox.getPlaceholder(), "placeholder");
	}

	protected void checkMultiSelection() {
		final MaterialComboBox<SampleModel> comboBox = new MaterialComboBox<>();
		comboBox.setKeyFactory(SampleModel::getName);
		final List<SampleModel> values = Arrays.asList(new SampleModel("id1", "name1"), new SampleModel("id2", "name2"),
				new SampleModel("id3", "name3"));
		comboBox.setAcceptableValues(values);
		RootPanel.get().add(comboBox);
		
		comboBox.setValues(Arrays.asList(new SampleModel("id1", "name1"), new SampleModel("id3", "name3")));
		List<SampleModel> selectedValues = comboBox.getSelectedValues();
		assertEquals(2, selectedValues.size());
		assertTrue(selectedValues.contains(new SampleModel("id1", "name1")));
		assertTrue(selectedValues.contains(new SampleModel("id3", "name3")));

		comboBox.setValues(Arrays.asList(new SampleModel("id2", "name2")));
		selectedValues = comboBox.getSelectedValues();
		assertEquals(1, selectedValues.size());
		assertTrue(selectedValues.contains(new SampleModel("id2", "name2")));

		comboBox.setValues(Collections.emptyList());
		assertTrue(comboBox.getSelectedValues().isEmpty()); 

	}

}
