In testing, the cycle starts at setup( it is same as comstructor).In body we use a testcase. and in the place of destrcutor or garbage collector we use teardown then.
@BeforeClass - it will be used once - runs at the load time.

Setup Annotation - @Before
body Annotation-@Test
teardown Annotation-@After 

running tests as follows
@Before --> @Test --> @After --- it will run everytime for all testcases

@AfterClass- it will be runned only once - at the run time.
