package propertyClass;

public final class RouteProperty {
     //Route 1 from()
     public static String route1FromCouchDb = "couchdb:http://localhost:5984/order";
     //Route 1 to()
     public static String route1ToFile = "file:/home/r2/Desktop/ofBizFile?fileExist=append&fileName=orders-${date:now:dd-MM-yyyy}.csv";
     //Route 2 from()
     public static String route2FromFile = "file:/home/r2/Desktop/ofBizFile?delay=1000&initialDelay=10000";
     //Route 2 to()
     public static String route2ToFile = "file:/home/r2/Desktop/ofBizFile/backUp?fileExist=append&fileName=orders-${date:now:dd-MM-yyyy'T'HH:mm:ss}.csv";
     //Route 2 to()
     public static String route2ToToken = "https4://hc-india.hotwax.co/api/control/getAuthenticationToken?throwExceptionOnFailure=false";
     //Route 3 to()
     public static String route3ToOfbiz = "https4://hc-india.hotwax.co/api/control/uploadAndImportFileFromCSVFile?throwExceptionOnFailure=false";

     //Route 2 and Route 3 setHeader
     public static String setUserNamePass = "USERNAME=00000&PASSWORD=0000";

     //DeadLetter path 

     public static String deadLetterPath = "file:/home/r2/Desktop/ofBizFile/exhaustedMessages";
}
