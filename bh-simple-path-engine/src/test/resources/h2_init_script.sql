CREATE SCHEMA IF NOT EXISTS USERS;
CREATE SCHEMA IF NOT EXISTS PARAM;
CREATE SCHEMA IF NOT EXISTS OPERATION;

CREATE TABLE IF NOT EXISTS PARAM.AXIS_TYPES (
	TYPE_ID VARCHAR(8) NOT NULL,
	TYPE_DESC VARCHAR(50) NOT NULL,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,
	CONSTRAINT PK_AXIS_TYPE PRIMARY KEY (TYPE_ID)
);

CREATE TABLE IF NOT EXISTS PARAM.AXIS (
 	AXIS_ID VARCHAR(8),
	AXIS_DES VARCHAR(20),
	TYPE_ID VARCHAR(8),
	IS_VALID BOOLEAN NOT NULL DEFAULT TRUE,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,
	CONSTRAINT PK_AXIS PRIMARY KEY (AXIS_ID),
	CONSTRAINT FK_AXIS_AXIS_TYPES FOREIGN KEY (TYPE_ID) REFERENCES PARAM.AXIS_TYPES(TYPE_ID)	
);

CREATE TABLE IF NOT EXISTS PARAM.AXIS_POSSIBLE_VALUES (
	AXIS_ID VARCHAR(8) NOT NULL,
	TYPE_ID VARCHAR(8) NOT NULL,
	VALUE_ID SERIAL,
	IS_VALID BOOLEAN NOT NULL DEFAULT TRUE,
	BOOLEAN_VALUE BOOLEAN,
	NUMERIC_VALUE DECIMAL,
	STRING_VALUE VARCHAR,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,
	-- IN H2 SERIAL ARE TREATED LIKE A PRIMARY KEY
	--CONSTRAINT PK_AXIS_POSSIBLE_VALUES PRIMARY KEY (AXIS_ID,VALUE_ID),
	CONSTRAINT FK_AXIS_AXIS_POSSIBLE_VALUES FOREIGN KEY (AXIS_ID) REFERENCES PARAM.AXIS(AXIS_ID)	
);

CREATE TABLE IF NOT EXISTS PARAM.OPERATORS (
	OPERATOR_ID VARCHAR(26)  NOT NULL,
	OPERATOR_DES VARCHAR(50) NOT NULL,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,
	CONSTRAINT PK_OPERATORS PRIMARY KEY (OPERATOR_ID)
);

CREATE TABLE IF NOT EXISTS PARAM.FORMULAS (
	FORMULA_ID SERIAL,
	FORMULA_CODE VARCHAR,
	FORMULA_KEY VARCHAR,
	IS_VALID BOOLEAN DEFAULT TRUE,
	FORMULA VARCHAR NOT NULL,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,
	CONSTRAINT PK_FORMULAS PRIMARY KEY (FORMULA_ID)
);

CREATE UNIQUE INDEX IF NOT EXISTS UI_PARAM_FORMULAS_CODE ON PARAM.FORMULAS (FORMULA_CODE);
CREATE UNIQUE INDEX IF NOT EXISTS UI_PARAM_FORMULAS_KEY ON PARAM.FORMULAS (FORMULA_KEY);

CREATE TABLE IF NOT EXISTS PARAM.LITERALS (
	LITERAL_CODE VARCHAR(26) NOT NULL,
	LITERAL_TYPE VARCHAR(26) NOT NULL,
	LITERAL_VALUE VARCHAR(255) NOT NULL,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,	
	CONSTRAINT PK_LITERALS PRIMARY KEY (LITERAL_CODE)
);


CREATE TABLE IF NOT EXISTS USERS.ROLES (
	ROLE_ID VARCHAR(8) NOT NULL,
	ROLE_DESCRIPTION VARCHAR(30) NOT NULL,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,	
	CONSTRAINT PK_ROLES PRIMARY KEY (ROLE_ID)
);

CREATE TABLE IF NOT EXISTS USERS.USERS (
	USER_ID VARCHAR(8) NOT NULL,
	ROLE_ID VARCHAR(8) NOT NULL,
	CREATION_DATE DATE NOT NULL DEFAULT CURRENT_DATE,
	EXPIRATION_DATE DATE,
	NAME VARCHAR(255) NOT NULL,
	CONSTRAINT PK_USERS PRIMARY KEY (USER_ID),
	CONSTRAINT FK_USERS_ROLES FOREIGN KEY(ROLE_ID) REFERENCES USERS.ROLES(ROLE_ID)
);


CREATE TABLE IF NOT EXISTS OPERATION.OPERATION (
	OPERATION_KEY VARCHAR NOT NULL,
	FORMULA_KEY VARCHAR,
	FORMULA VARCHAR,
	APPLIED_FORMULA VARCHAR,
	OPERATION_RELEVANT_INFO VARCHAR,
	AUDIT_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	AUDIT_USER VARCHAR(26) NOT NULL,
	CONSTRAINT PK_OPERATION PRIMARY KEY (OPERATION_KEY),
	CONSTRAINT FK_OPERATION_FORMULA FOREIGN KEY (FORMULA_KEY) REFERENCES PARAM.FORMULAS (FORMULA_KEY)	
);
/*
	DATA
*/

MERGE INTO PARAM.OPERATORS (OPERATOR_ID,OPERATOR_DES,AUDIT_USER) VALUES ('EQUALS','EXACT VALUE','INITIAL');
MERGE INTO PARAM.OPERATORS (OPERATOR_ID,OPERATOR_DES,AUDIT_USER) VALUES ('GREAT','GREAT THAN','INITIAL');
MERGE INTO PARAM.OPERATORS (OPERATOR_ID,OPERATOR_DES,AUDIT_USER) VALUES ('LESS','LESS THAN','INITIAL');
MERGE INTO PARAM.OPERATORS (OPERATOR_ID,OPERATOR_DES,AUDIT_USER) VALUES ('GREATOREQUAL','GREAT OR EQUAL THAN','INITIAL');
MERGE INTO PARAM.OPERATORS (OPERATOR_ID,OPERATOR_DES,AUDIT_USER) VALUES ('LESSTOREQUAL','LESS OR EQUAL THAN','INITIAL');
MERGE INTO PARAM.OPERATORS (OPERATOR_ID,OPERATOR_DES,AUDIT_USER) VALUES ('BETWEEN','BETWEEN TWO VALUES','INITIAL');
