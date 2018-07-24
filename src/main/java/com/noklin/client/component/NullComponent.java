package com.noklin.client.component;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class NullComponent extends Component{
	NullComponent(ComponentConfig config) {
		super(config);
	}

	public Widget asWidget() {
		return new HTML("<div>Not implemented</div>");
	}
}
