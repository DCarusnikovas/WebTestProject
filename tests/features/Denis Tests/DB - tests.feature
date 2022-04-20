Feature: DB tests


@TCDenisDB1
Scenario: DB -test

And I execute query "select result from sql4437652.Automation where id=1" and save result to state "State.FirstQueryResult"

And I execute query "select result from sql4437652.Automation where result ='PASSED'" and save rows amount

And I execute query "select result from sql4437652.Automation where result ='PASSED'" and check that query returns "State.queryrows" rows

And I execute query "select result from sql4437652.Automation where result ='PASS'" and check that query returns "1" rows
And I execute query "select result from sql4437652.Automation where result ='PAS'" and check that query returns "0" rows

And I execute query "select result from sql4437652.Automation where id in (1,2)" and check that query returns "2" rows






 