import munit.FunSuite

class ObservabilityUtilsSpec extends FunSuite {

  override def afterEach(context: AfterEach): Unit = {
    System.clearProperty("java.version") // Clear the property after each test
  }

  test("JVMVersion should handle modern Java versions without a dot") {
    System.setProperty("java.version", "17")
    assertEquals(ObservabilityUtils.JVMVersion, "17")
  }

  test("JVMVersion should handle modern Java versions with minor version") {
    System.setProperty("java.version", "11.0.2")
    assertEquals(ObservabilityUtils.JVMVersion, "11")
  }

  test("JVMVersion should handle legacy Java version format") {
    System.setProperty("java.version", "1.8.0_291")
    assertEquals(ObservabilityUtils.JVMVersion, "8")
  }

  test("JVMVersion should return 'Unknown' for invalid format") {
    System.setProperty("java.version", "invalid-version")
    assertEquals(ObservabilityUtils.JVMVersion, "Unknown")
  }

  test("JVMVersion should return 'Unknown' for null value") {
    System.clearProperty("java.version") // Ensure the property is unset
    assertEquals(ObservabilityUtils.JVMVersion, "")
  }

  test("JVMVersion should return 'Unknown' for empty version") {
    System.setProperty("java.version", "")
    assertEquals(ObservabilityUtils.JVMVersion, "")
  }

  test("JVMVersion should handle pre-release versions") {
    System.setProperty("java.version", "21-ea")
    assertEquals(ObservabilityUtils.JVMVersion, "21")
  }
}
