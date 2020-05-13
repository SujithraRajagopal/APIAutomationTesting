package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src\\main\\java\\features", glue = { "stepDefinitions" }, tags = { "~@Ignore" }, format = {
		"pretty", "html:target/cucumber-reports/cucumber-pretty", "json:target/cucumber-reports/cucumberreports.json",
		"rerun:target/cucumber-reports/rerun.txt" }, plugin = "json:target/cucumber-reports/cucumberreports.json" )

public class Runner {

	private TestNGCucumberRunner TestNGCucumberRunner;

	/*
	 * @BeforeClass(alwaysRun = true) public void setUpClass() throws Exception {
	 * TestNGCucumberRunner = new TestNGCucumberRunner(this.getClass()); }
	 */

	@Test(groups = "cucumber", description = "Runs cucumber feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		TestNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
		// TestNGCucumberRunner.finish();
	}

	@DataProvider(name = "features")
	public Object[][] features() {
		TestNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		return TestNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception{
		TestNGCucumberRunner.finish();
	}

}
