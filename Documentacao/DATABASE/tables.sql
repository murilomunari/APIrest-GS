CREATE TABLE "TB_AREAS"(
                           "ID_AREA" NUMBER(19,0),
                           "LOCAL" VARCHAR2(255)
);

CREATE UNIQUE INDEX  "IDX_TB_AREAS_ID" ON  "TB_AREAS" ("ID_AREA");

ALTER TABLE  "TB_AREAS" MODIFY ("ID_AREA" NOT NULL ENABLE);
ALTER TABLE  "TB_AREAS" ADD PRIMARY KEY ("ID_AREA");

CREATE SEQUENCE  "SQ_AREA"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

DROP TABLE "TB_AREAS";


CREATE TABLE "TB_PACIENTE"(
                              "ID_PACIENTE" NUMBER(19,0),
                              "NM_PACIENTE" VARCHAR2(255),
                              "CPF" VARCHAR2(11),
                              "LAUDO" VARCHAR2(255),
                              "NASCIMENTO" DATE
);

CREATE UNIQUE INDEX  "IDX_TB_PACIENTE_ID" ON  "TB_PACIENTE" ("ID_PACIENTE");

ALTER TABLE  "TB_PACIENTE" MODIFY ("ID_PACIENTE" NOT NULL ENABLE);
ALTER TABLE  "TB_PACIENTE" ADD PRIMARY KEY ("ID_PACIENTE");

CREATE SEQUENCE  "SQ_PACIENTE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
DROP SEQUENCE SQ_PACIENTE;

DROP TABLE TB_PACIENTE;

CREATE TABLE "TB_EmpresaTerceira"(
                                     "ID_EMPRESA" NUMERIC(19,0),
                                     "NM_EMPRESA" VARCHAR2(255),
                                     "NR_CNPJ" VARCHAR2(14),
                                     "RANO" NUMBER(19,0)
);
CREATE UNIQUE INDEX  "IDX_TB_EmpresaTerceira_ID" ON  "TB_EmpresaTerceira" ("ID_EMPRESA");

ALTER TABLE  "TB_EmpresaTerceira" MODIFY ("ID_EMPRESA" NOT NULL ENABLE);
ALTER TABLE  "TB_EmpresaTerceira" ADD PRIMARY KEY ("ID_EMPRESA");

CREATE SEQUENCE  "SQ_EMPRESA"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
DROP TABLE "TB_EmpresaTerceira";

CREATE TABLE "TB_RAMO"(
                          "ID_RAMO" NUMBER(19,0),
                          "NM_RAMO" VARCHAR2(255)
);
CREATE UNIQUE INDEX  "IDX_TB_RAMO_ID" ON  "TB_RAMO" ("ID_RAMO");

ALTER TABLE  "TB_RAMO" MODIFY ("ID_RAMO" NOT NULL ENABLE);
ALTER TABLE  "TB_RAMO" ADD PRIMARY KEY ("ID_RAMO");

CREATE SEQUENCE  "SQ_RAMO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
DROP TABLE "TB_RAMO";

CREATE TABLE "TB_SERVICOS"(
                              "ID_SERVICO" NUMBER(19,0),
                              "DS_SERVICO" VARCHAR2(255),
                              "TIPO" VARCHAR2(255)
);
CREATE UNIQUE INDEX  "IDX_TB_SERVICOS_ID" ON  "TB_SERVICOS" ("ID_SERVICO");

ALTER TABLE  "TB_SERVICOS" MODIFY ("ID_SERVICO" NOT NULL ENABLE);
ALTER TABLE  "TB_SERVICOS" ADD PRIMARY KEY ("ID_SERVICO");

CREATE SEQUENCE  "SQ_SERVICO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

DROP TABLE "TB_SERVICOS";

CREATE TABLE "TB_FUNCIONARIOS"(
                                  "ID_FUNCIONARIO" NUMBER(19,0),
                                  "NM_FUNCIONARIO" VARCHAR2(255),
                                  "NR_CPF" VARCHAR2(11),
                                  "SETOR" VARCHAR2(255),
                                  "EMPRESA" NUMBER(19,0)
);
CREATE UNIQUE INDEX  "IDX_TB_FUNCIONARIOS_ID" ON  "TB_FUNCIONARIOS" ("ID_FUNCIONARIO");

ALTER TABLE  "TB_FUNCIONARIOS" MODIFY ("ID_FUNCIONARIO" NOT NULL ENABLE);
ALTER TABLE  "TB_FUNCIONARIOS" ADD PRIMARY KEY ("ID_FUNCIONARIO");

CREATE SEQUENCE  "SQ_FUNCIONARIO"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;


ALTER TABLE  "TB_EmpresaTerceira" ADD CONSTRAINT "TB_EmpresaTerceira_FK_RAMO" FOREIGN KEY ("RAMO")
    REFERENCES  "TB_RAMO" ("ID_RAMO") ENABLE;

ALTER TABLE  "TB_FUNCIONARIOS" ADD CONSTRAINT "TB_FUNCIONARIOS_FK_EmpresaTerceira" FOREIGN KEY ("EMPRESA")
    REFERENCES  "TB_EmpresaTerceira" ("ID_EMPRESA") ENABLE;