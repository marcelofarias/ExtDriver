package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

public abstract class AbstractFormFixture<T extends AbstractFormFixture<T>> extends ExtComponentFixture {
	
	public AbstractFormFixture(String id, ExtDriver driver) {
		super(id, driver);
	}

	public AbstractFormFixture(String id, ExtComponentFixture parent, ExtDriver driver) {
		super(id, parent, driver);
	}
	
	private Condition isExpandedCondition = new Condition() {
		@Override
		public boolean isSatisfied() {
			return isExpanded();
		}
	};
	
	private Condition isCollapsedCondition = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isExpanded();
		}
	};
	
	private Condition isNotCollapsingOrExpanding = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isCollapsingOrExpanding();
		}
	};

	public T expand() {
		if (!isExpanded()) {
			getCollapseTool().click();
			isNotCollapsingOrExpanding.waitUntilSatisfied("Form was not expanded");
		}
		return (T) this;
	}
	
	public T collapse() {
		if (isExpanded()) {
			getCollapseTool().click();
			isNotCollapsingOrExpanding.waitUntilSatisfied("Form was not collapsed");
		}
		return (T) this;
	}
	
	public T requireExpanded() {
		isExpandedCondition.waitUntilSatisfied("Form should be expanded");
		return (T) this;
	}

	public T requireCollapsed() {
		isCollapsedCondition.waitUntilSatisfied("Form should be collapsed");
		return (T) this;
	}

	private boolean isExpanded() {
		Object collapsed = getDriver().executeScript("return Ext.getCmp(arguments[0]).collapsed", getId());
		return Boolean.FALSE.equals(collapsed);
	}
	
	private boolean isCollapsingOrExpanding() {
		Object isCollapsingOrExpanding = getDriver().executeScript("return Ext.getCmp(arguments[0]).isCollapsingOrExpanding", getId());
		return !(new Long(0).equals(isCollapsingOrExpanding));
	}

	private WebElement getCollapseTool() {
		return (WebElement) getDriver().executeScript("return Ext.get(arguments[0]).select('.x-panel-header .x-tool img').elements[0]", getId());
	}

}