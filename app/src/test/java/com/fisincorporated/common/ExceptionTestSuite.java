package com.fisincorporated.common;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)
@Categories.IncludeCategory({ExceptionTest.class})
@Suite.SuiteClasses({StationControlTest.class })
public class ExceptionTestSuite {

}
