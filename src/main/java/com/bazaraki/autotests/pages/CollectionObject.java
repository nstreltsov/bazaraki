package com.bazaraki.autotests.pages;

import org.openqa.selenium.SearchContext;

/**
 * @author Nikolay Streltsov on 17.05.2020
 */
public abstract class CollectionObject extends AbstractPageObject implements ICollectionObject{

    public CollectionObject(){}

    public CollectionObject(SearchContext searchContext){
        super(searchContext);
    }

    @Override
    public boolean isLoaded() {
        return true;
    }
}
