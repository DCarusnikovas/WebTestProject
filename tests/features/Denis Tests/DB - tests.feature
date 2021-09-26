Feature: DB tests


@TCDenisDB1
Scenario: DB -test

And I execute query "select result from sql4437652.Automation where id=1" and save result to state "State.FirstQueryResult"



And I execute query "select result from sql4437652.Automation where id=0" and check that query returns "0" rows

And I execute query "select result from sql4437652.Automation where id=1" and save rows amount




 