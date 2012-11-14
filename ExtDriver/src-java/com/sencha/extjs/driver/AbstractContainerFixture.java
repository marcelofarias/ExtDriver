package com.sencha.extjs.driver;

import org.openqa.selenium.WebElement;

import com.sencha.extjs.driver.locator.ComponentLocator;

public abstract class AbstractContainerFixture<T extends AbstractContainerFixture<T>> extends ExtComponentFixture {
	
	public AbstractContainerFixture(String id, ExtDriver driver) {
		super(id, driver);
	}

	public AbstractContainerFixture(String id, ExtComponentFixture scope, ExtDriver driver) {
		super(id, scope, driver);
	}
	
	public AbstractContainerFixture(ComponentLocator locator, ExtComponentFixture scope, ExtDriver driver) {
		super(locator, scope, driver);
	}
	
	public AbstractContainerFixture() {
		
	}
	
	protected abstract String getContainerPresentationName();
	
	private Condition isExpandedCondition = new Condition() {
		@Override
		public boolean isSatisfied() {
			return isExpanded();
		}
		@Override
		public String getErrorMessage() {
			return String.format("%s should be expanded", getContainerPresentationName());
		}
	};
	
	private Condition isCollapsedCondition = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isExpanded();
		}
		@Override
		public String getErrorMessage() {
			return String.format("%s should be collapsed", getContainerPresentationName());
		}
	};
	
	private Condition isNotCollapsing = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isCollapsingOrExpanding();
		}
		@Override
		public String getErrorMessage() {
			return String.format("%s was not collapsed", getContainerPresentationName());
		}
	};
	
	private Condition isNotExpanding = new Condition() {
		@Override
		public boolean isSatisfied() {
			return !isCollapsingOrExpanding();
		}
		@Override
		public String getErrorMessage() {
			return String.format("%s was not expanded", getContainerPresentationName());
		}
	};
	
	public T requireTitle(final String title) {
		new Condition() {
			@Override
			public boolean isSatisfied() {
				return title.equals(getTitle());
			}
			@Override
			public String getErrorMessage() {
				return String.format("Expected title [%s], but found [%s]", title, getTitle());
			}
		}.waitUntilSatisfied();
		
		return (T) this;
	}

	public T expand() {
		if (!isExpanded()) {
			getCollapseToolElement().click();
			isNotExpanding.waitUntilSatisfied();
			isExpandedCondition.waitUntilSatisfied();
		}
		return (T) this;
	}
	
	public T collapse() {
		if (isExpanded()) {
			getCollapseToolElement().click();
			isNotCollapsing.waitUntilSatisfied();
			isCollapsedCondition.waitUntilSatisfied();
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

	protected boolean isExpanded() {
		Object collapsed = getDriver().executeScript("return Ext.getCmp(arguments[0]).collapsed", getId());
		return Boolean.FALSE.equals(collapsed);
	}
	
	protected abstract boolean isCollapsingOrExpanding();

	protected abstract WebElement getCollapseToolElement();
	
	protected abstract String getTitle();

}