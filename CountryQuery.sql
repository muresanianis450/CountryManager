USE CountryManagerDB;
GO

--See what tables exist

SELECT TABLE_SCHEMA, TABLE_NAME
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_TYPE = 'BASE TABLE';

SELECT @@SERVICENAME AS ServiceName, SERVERPROPERTY('InstanceName') AS InstanceName;


--BrutForce the tcp/ip connection since SQL MANAGER is not working properly
EXEC xp_instance_regwrite
    N'HKEY_LOCAL_MACHINE',
    N'SOFTWARE\Microsoft\MSSQLServer\MSSQLServer\SuperSocketNetLib\Tcp',
    N'Enabled',
    REG_DWORD,
    1;




-- See All Countries
SELECT * FROM dbo.countries;


--See Country With ID = 9
SELECT * FROM dbo.countries
WHERE id = 9;

--See Cyprus
SELECT * FROM dbo.countries
WHERE country = 'Cyprus';
