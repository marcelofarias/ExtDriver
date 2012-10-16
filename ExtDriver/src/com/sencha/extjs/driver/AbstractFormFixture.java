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
		@Override
		public String getErrorMessage() {
			return "Form should be expanded";
		}
	};
	
	private Condition isCollapsedCondition = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isExpanded();
		}
		@Override
		public String getErrorMessage() {
			return "Form should be collapsed";
		}
	};
	
	private Condition isNotCollapsing = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isCollapsingOrExpanding();
		}
		@Override
		public String getErrorMessage() {
			return "Form was not collapsed";
		}
	};
	
	private Condition isNotExpanding = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isCollapsingOrExpanding();
		}
		@Override
		public String getErrorMessage() {
			return "Form was not expanded";
		}
	};

	public T expand() {
		if (!isExpanded()) {
			getCollapseTool().click();
			isNotExpanding.waitUntilSatisfied();
		}
		return (T) this;
	}
	
	public T collapse() {
		if (isExpanded()) {
			getCollapseTool().click();
			isNotCollapsing.waitUntilSatisfied();
		}
		return (T) this;
	}
	
	public T requireExpanded() {
		isExpandedCondition.waitUntilSatisfied();
		return (T) this;
	}

	public T requireCollapsed() {
		isCollapsedCondition.waitUntilSatisfied();
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