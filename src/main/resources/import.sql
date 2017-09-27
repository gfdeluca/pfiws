# Volcado de tabla AppConfiguration
# ------------------------------------------------------------

DROP TABLE IF EXISTS `AppConfiguration`;

CREATE TABLE `AppConfiguration` (
  `clave` varchar(255) NOT NULL,
  `valor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`clave`)
) ENGINE=Aria DEFAULT CHARSET=latin1;

# Volcado de tabla CalculatedCodonsPoblations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CalculatedCodonsPoblations`;

CREATE TABLE `CalculatedCodonsPoblations` (
  `idCalculatedCodonPoblation` bigint zerofill NOT NULL,
  `energy` double DEFAULT NULL,
  `poblation` double DEFAULT NULL,
  `poblationLn` double DEFAULT NULL,
  `idCodon` bigint zerofill DEFAULT NULL,
  `idOrganismPoblation` bigint zerofill DEFAULT NULL,
  PRIMARY KEY (`idCalculatedCodonPoblation`)
) ENGINE=Aria DEFAULT CHARSET=latin1;

CREATE INDEX idx_CalculatedCodonsPoblations_idOrganismPoblation ON CalculatedCodonsPoblations (idOrganismPoblation);

# Volcado de tabla Codons
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Codons`;

CREATE TABLE `Codons` (
  `idCodon` bigint zerofill NOT NULL AUTO_INCREMENT,
  `aminoacidFullName` varchar(100) DEFAULT NULL,
  `aminoacidLetter` varchar(1) DEFAULT NULL,
  `aminoacidShotName` varchar(3) DEFAULT NULL,
  `name` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`idCodon`)
) ENGINE=Aria DEFAULT CHARSET=latin1;

# Volcado de tabla ExperimentalCodonsPoblations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ExperimentalCodonsPoblations`;

CREATE TABLE `ExperimentalCodonsPoblations` (
  `idExperimentalCodonPoblation` bigint zerofill NOT NULL AUTO_INCREMENT,
  `poblation` double DEFAULT NULL,
  `poblationLn` double DEFAULT NULL,
  `idCodon` bigint zerofill DEFAULT NULL,
  `idOrganism` bigint zerofill DEFAULT NULL,
  PRIMARY KEY (`idExperimentalCodonPoblation`)
) ENGINE=Aria DEFAULT CHARSET=latin1;

# Volcado de tabla Global
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Global`;

CREATE TABLE `Global` (
  `idGlobal` bigint zerofill NOT NULL AUTO_INCREMENT,
  `bestPearsonCoefficient` double DEFAULT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `lastRunOk` int(11) DEFAULT NULL,
  PRIMARY KEY (`idGlobal`)
) ENGINE=Aria DEFAULT CHARSET=latin1;

# Volcado de tabla Organisms
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Organisms`;

CREATE TABLE `Organisms` (
  `idOrganism` bigint zerofill NOT NULL ,
  `bestPearson` double DEFAULT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `porcGC` double DEFAULT NULL,
  PRIMARY KEY (`idOrganism`)
) ENGINE=Aria DEFAULT CHARSET=latin1;

# Volcado de tabla OrganismsPoblations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `OrganismsPoblations`;

CREATE TABLE `OrganismsPoblations` (
  `idOrganismPoblation` bigint zerofill NOT NULL,
  `gamma` double DEFAULT NULL,
  `idTransition` bigint zerofill DEFAULT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `pearsonCoefficient` double DEFAULT NULL,
  `idOrganism` bigint zerofill DEFAULT NULL,
  PRIMARY KEY (`idOrganismPoblation`)
) ENGINE=Aria DEFAULT CHARSET=latin1;

CREATE INDEX idx_OrganismsPoblations_idOrganism ON OrganismsPoblations (idOrganism);
CREATE INDEX idx_OrganismsPoblations_idOrganism_Gamma ON OrganismsPoblations (idOrganism, Gamma);

/* INSERTO LAS CONFIGURACIONES */
INSERT INTO AppConfiguration (clave, valor) VALUES ('process.gammas', '100000');
INSERT INTO AppConfiguration (clave, valor) VALUES ('process.step', '0.002');
INSERT INTO AppConfiguration (clave, valor) VALUES ('process.tmp.path', '/home/guido');
INSERT INTO AppConfiguration (clave, valor) VALUES ('statement.flush', '150000');

/* INSERTO TODOS LOS CODONES */
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CGA', '', '', 'R');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CGC', '', '', 'R');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CGG', '', '', 'R');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CGU', '', '', 'R');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AGA', '', '', 'R');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AGG', '', '', 'R');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CUA', '', '', 'L');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CUC', '', '', 'L');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CUG', '', '', 'L');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CUU', '', '', 'L');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UUA', '', '', 'L');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UUG', '', '', 'L');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UCA', '', '', 'S');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UCC', '', '', 'S');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UCG', '', '', 'S');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UCU', '', '', 'S');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AGC', '', '', 'S');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AGU', '', '', 'S');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('ACA', '', '', 'T');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('ACC', '', '', 'T');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('ACG', '', '', 'T');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('ACU', '', '', 'T');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CCA', '', '', 'P');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CCC', '', '', 'P');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CCG', '', '', 'P');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CCU', '', '', 'P');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GCA', '', '', 'A');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GCC', '', '', 'A');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GCG', '', '', 'A');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GCU', '', '', 'A');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GGA', '', '', 'G');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GGC', '', '', 'G');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GGG', '', '', 'G');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GGU', '', '', 'G');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GUA', '', '', 'V');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GUC', '', '', 'V');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GUG', '', '', 'V');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GUU', '', '', 'V');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AAA', '', '', 'K');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AAG', '', '', 'K');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AAC', '', '', 'N');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AAU', '', '', 'N');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CAA', '', '', 'Q');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CAG', '', '', 'Q');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CAC', '', '', 'H');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('CAU', '', '', 'H');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GAA', '', '', 'E');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GAG', '', '', 'E');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GAC', '', '', 'D');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('GAU', '', '', 'D');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UAC', '', '', 'Y');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UAU', '', '', 'Y');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UGC', '', '', 'C');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UGU', '', '', 'C');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UUC', '', '', 'F');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UUU', '', '', 'F');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AUA', '', '', 'I');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AUC', '', '', 'I');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AUU', '', '', 'I');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('AUG', '', '', 'M');
INSERT INTO Codons (name, aminoacidFullName, aminoacidShotName, aminoacidLetter) VALUES ('UGG', '', '', 'W');

/* INSERTO EL PRIMER ORGANISMO */
INSERT INTO Organisms (name, porcGC, bestPearson, lastUpdate) VALUES ('Drosophila guanche', 0.56277, 0, now());

/* INSERTO LAS POBLACIONES EXPERIMENTALES DEL PRIMER ORGANISMO */
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,1,0.008907892,-4.72081761);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,2,0.018053329,-4.014425202);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,3,0.005760437,-5.156741925);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,4,0.013539996,-4.302107275);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,5,0.000574064,-7.462769362);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,6,0.003167251,-5.754891377);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,7,0.00190035,-6.265717001);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,8,0.01314409,-4.331783043);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,9,0.037413148,-3.285733084);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,10,0.002612982,-5.94726327);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,11,0.000989766,-6.918042187);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,12,0.011817804,-4.438148079);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,13,0.009620524,-4.643856568);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,14,0.015836253,-4.145453465);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,15,0.046499198,-3.068320208);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,16,0.005938595,-5.126282718);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,17,0.010768652,-4.531115945);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,18,0.007640992,-4.874227823);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,19,0.017736604,-4.032124779);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,20,0.018607597,-3.984185317);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,21,0.025100461,-3.684869057);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,22,0.0071857,-4.935662358);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,23,0.014787101,-4.214000007);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,24,0.034780371,-3.358702104);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,25,0.02814894,-3.570245582);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,26,0.00625532,-5.074322979);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,27,0.005899004,-5.132971706);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,28,0.032583091,-3.423961811);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,29,0.011798009,-4.439824525);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,30,0.006413683,-5.049321676);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,31,0.007027337,-4.957947403);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,32,0.041530574,-3.181325403);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,33,0.002217075,-6.111566321);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,34,0.014391195,-4.241138715);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,35,0.00405804,-5.507055213);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,36,0.011639646,-4.453338244);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,37,0.031019261,-3.47314695);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,38,0.007858741,-4.846128912);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,39,0.007779559,-4.85625558);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,40,0.048003642,-3.036478389);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,41,0.027040402,-3.610423152);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,42,0.022170754,-3.808981228);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,43,0.003939268,-5.536760368);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,44,0.030860898,-3.478265323);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,45,0.011817804,-4.438148079);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,46,0.010590494,-4.547798445);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,47,0.007146109,-4.941187234);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,48,0.04568759,-3.085928565);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,49,0.020745492,-3.875426327);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,50,0.030247244,-3.498350223);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,51,0.023041748,-3.770447564);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,52,0.014886078,-4.207328868);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,53,0.013500406,-4.305035534);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,54,0.002890116,-5.846458571);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,55,0.023180316,-3.764451829);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,56,0.009442366,-4.662548701);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,57,0.009561138,-4.650048539);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,58,0.028406279,-3.561145064);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,59,0.019142071,-3.955866697);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,60,0.037729873,-3.277303108);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (1,61,0.008967278,-4.714173067);

/* INSERTO EL SEGUNDO ORGANISMO */
INSERT INTO Organisms (name, porcGC, bestPearson, lastUpdate) VALUES ('Clostridium perfringens', 0.295006427278, 0, now());

/* INSERTO LAS POBLACIONES EXPERIMENTALES DEL SEGUNDO ORGANISMO */
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,1,0.00021,-8.489526473);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,2,0.00009,-9.265561313);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,3,0.00003,-10.48195664);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,4,0.00113,-6.787882367);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,5,0.02873,-3.54979334);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,6,0.00317,-5.752726859);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,7,0.00791,-4.839163435);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,8,0.00046,-7.688748628);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,9,0.00048,-7.641417254);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,10,0.01909,-3.958761506);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,11,0.06186,-2.782926003);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,12,0.00385,-5.560456186);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,13,0.02004,-3.910082104);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,14,0.00223,-6.103582499);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,15,0.00037,-7.897959085);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,16,0.01593,-4.139248537);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,17,0.00535,-5.230119605);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,18,0.01813,-4.010254549);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,19,0.01933,-3.945929512);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,20,0.00279,-5.880125353);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,21,0.00073,-7.227070375);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,22,0.02329,-3.759677887);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,23,0.01453,-4.231418491);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,24,0.00055,-7.51368121);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,25,0.00038,-7.863952717);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,26,0.01179,-4.440115883);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,27,0.01992,-3.91598769);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,28,0.00408,-5.500921813);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,29,0.00114,-6.780654664);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,30,0.02933,-3.529267828);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,31,0.03895,-3.245407334);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,32,0.00343,-5.674866537);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,33,0.00542,-5.21689448);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,34,0.01879,-3.974243697);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,35,0.02627,-3.639317825);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,36,0.00136,-6.601252316);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,37,0.00372,-5.595373992);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,38,0.03422,-3.374974888);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,39,0.06486,-2.735529885);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,40,0.02814,-3.570665949);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,41,0.01117,-4.494458279);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,42,0.05280,-2.941312982);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,43,0.01698,-4.075833263);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,44,0.00271,-5.910688003);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,45,0.00246,-6.007464775);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,46,0.01044,-4.561943339);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,47,0.06282,-2.767502304);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,48,0.01859,-3.984867469);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,49,0.00708,-4.950050462);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,50,0.04824,-3.031562195);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,51,0.00656,-5.026457476);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,52,0.03356,-3.39427851);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,53,0.00230,-6.074221646);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,54,0.00890,-4.721347594);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,55,0.00889,-4.722923432);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,56,0.03703,-3.296127801);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,57,0.05957,-2.820566821);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,58,0.00433,-5.442032265);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,59,0.03178,-3.449001452);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,60,0.02474,-3.699197849);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (2,61,0.00694,-4.970209412);

/* INSERTO EL TERCER ORGANISMO */
INSERT INTO Organisms (name, porcGC, bestPearson, lastUpdate) VALUES ('Sterkiella histriomuscorum', 0.40203836369, 0, now());

/* INSERTO LAS POBLACIONES EXPERIMENTALES DEL TERCER ORGANISMO */
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,1,0.00052,-7.57124);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,2,0.00173,-6.35822);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,3,0.00014,-8.87052);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,4,0.00286,-5.85826);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,5,0.03498,-3.35307);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,6,0.00272,-5.90869);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,7,0.00829,-4.79299);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,8,0.01845,-3.99278);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,9,0.00323,-5.73503);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,10,0.01779,-4.02896);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,11,0.01690,-4.08026);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,12,0.01714,-4.06650);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,13,0.02276,-3.78293);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,14,0.00796,-4.83334);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,15,0.00126,-6.67330);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,16,0.01358,-4.29925);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,17,0.00833,-4.78735);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,18,0.01236,-4.39319);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,19,0.01349,-4.30617);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,20,0.01611,-4.12849);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,21,0.00070,-7.26108);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,22,0.02416,-3.72303);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,23,0.02107,-3.85989);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,24,0.00487,-5.32474);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,25,0.00061,-7.40419);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,26,0.00824,-4.79865);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,27,0.01634,-4.11406);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,28,0.01475,-4.21656);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,29,0.00098,-6.92461);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,30,0.02894,-3.54265);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,31,0.02168,-3.83141);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,32,0.00894,-4.71686);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,33,0.00220,-6.11899);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,34,0.03184,-3.44704);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,35,0.01185,-4.43575);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,36,0.01540,-4.17308);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,37,0.00679,-4.99240);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,38,0.02636,-3.63586);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,39,0.03797,-3.27087);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,40,0.05296,-2.93828);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,41,0.03072,-3.48297);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,42,0.03048,-3.49063);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,43,0.02800,-3.57554);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,44,0.00599,-5.11710);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,45,0.00894,-4.71686);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,46,0.01044,-4.56196);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,47,0.03811,-3.26717);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,48,0.02781,-3.58226);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,49,0.01882,-3.97268);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,50,0.03783,-3.27457);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,51,0.01714,-4.06650);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,52,0.01864,-3.98268);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,53,0.01007,-4.59850);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,54,0.00595,-5.12495);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,55,0.03268,-3.42092);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,56,0.01915,-3.95542);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,57,0.01157,-4.45975);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,58,0.02383,-3.73669);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,59,0.03147,-3.45888);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,60,0.02468,-3.70193);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (3,61,0.01044,-4.56196);

/* INSERTO EL CUARTO ORGANISMO */
INSERT INTO Organisms (name, porcGC, bestPearson, lastUpdate) VALUES ('Rhodopseudomonas palustris HaA2', 0.666721405663, 0, now());

/* INSERTO LAS POBLACIONES EXPERIMENTALES DEL CUARTO ORGANISMO */
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,1,0.00320,-5.74613);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,2,0.04012,-3.21590);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,3,0.01912,-3.95677);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,4,0.00482,-5.33515);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,5,0.00088,-7.03055);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,6,0.00215,-6.14424);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,7,0.00094,-6.97165);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,8,0.02948,-3.52415);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,9,0.05793,-2.84859);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,10,0.00278,-5.88459);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,11,0.00031,-8.09256);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,12,0.00794,-4.83568);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,13,0.00131,-6.64130);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,14,0.01063,-4.54370);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,15,0.02454,-3.70739);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,16,0.00111,-6.80449);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,17,0.01591,-4.14087);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,18,0.00188,-6.27437);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,19,0.00176,-6.34437);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,20,0.03234,-3.43142);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,21,0.01868,-3.98009);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,22,0.00179,-6.32795);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,23,0.00177,-6.33594);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,24,0.01109,-4.50208);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,25,0.03890,-3.24679);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,26,0.00183,-6.30504);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,27,0.00621,-5.08235);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,28,0.05618,-2.87923);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,29,0.06262,-2.77069);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,30,0.00505,-5.28850);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,31,0.00482,-5.33528);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,32,0.06483,-2.73595);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,33,0.00943,-4.66398);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,34,0.00615,-5.09061);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,35,0.00107,-6.83829);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,36,0.03643,-3.31248);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,37,0.03409,-3.37875);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,38,0.00344,-5.67111);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,39,0.00579,-5.15225);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,40,0.02806,-3.57349);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,41,0.01764,-4.03764);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,42,0.00855,-4.76187);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,43,0.00478,-5.34323);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,44,0.02716,-3.60597);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,45,0.01153,-4.46291);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,46,0.00776,-4.85899);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,47,0.01886,-3.97086);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,48,0.03176,-3.44970);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,49,0.04082,-3.19867);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,50,0.01756,-4.04223);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,51,0.01216,-4.40946);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,52,0.01004,-4.60158);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,53,0.00741,-4.90434);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,54,0.00092,-6.98903);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,55,0.03220,-3.43567);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,56,0.00429,-5.45049);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,57,0.00055,-7.51350);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,58,0.04783,-3.04009);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,59,0.00480,-5.33972);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,60,0.02343,-3.75385);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (4,61,0.01261,-4.37328);

/* INSERTO EL QUINTO ORGANISMO */
INSERT INTO Organisms (name, porcGC, bestPearson, lastUpdate) VALUES ('Streptomyces ambofaciens', 0.717826570586, 0, now());

/* INSERTO LAS POBLACIONES EXPERIMENTALES DEL QUINTO ORGANISMO */
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,1,0.00326,-5.72465);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,2,0.04365,-3.13161);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,3,0.02667,-3.62414);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,4,0.00665,-5.01315);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,5,0.00096,-6.94941);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,6,0.00384,-5.56102);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,7,0.00072,-7.23990);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,8,0.03407,-3.37929);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,9,0.06373,-2.75316);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,10,0.00236,-6.04836);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,11,0.00012,-9.02049);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,12,0.00326,-5.72712);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,13,0.00181,-6.31689);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,14,0.01808,-4.01297);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,15,0.00984,-4.62111);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,16,0.00114,-6.77271);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,17,0.01569,-4.15501);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,18,0.00156,-6.46068);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,19,0.00267,-5.92642);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,20,0.04518,-3.09712);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,21,0.01455,-4.23022);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,22,0.00189,-6.26895);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,23,0.00177,-6.33491);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,24,0.02943,-3.52578);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,25,0.02829,-3.56517);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,26,0.00278,-5.88499);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,27,0.01031,-4.57470);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,28,0.08693,-2.44263);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,29,0.04008,-3.21676);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,30,0.00526,-5.24849);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,31,0.00865,-4.75032);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,32,0.05841,-2.84033);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,33,0.01452,-4.23244);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,34,0.00875,-4.73828);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,35,0.00264,-5.93552);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,36,0.04087,-3.19744);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,37,0.03123,-3.46624);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,38,0.00233,-6.06211);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,39,0.00204,-6.19515);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,40,0.01504,-4.19698);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,41,0.01523,-4.18420);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,42,0.00103,-6.87651);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,43,0.00228,-6.08309);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,44,0.02820,-3.56859);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,45,0.02643,-3.63324);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,46,0.00277,-5.88790);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,47,0.01400,-4.26862);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,48,0.03984,-3.22281);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,49,0.05708,-2.86337);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,50,0.00521,-5.25774);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,51,0.01824,-4.00409);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,52,0.00114,-6.77271);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,53,0.00855,-4.76157);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,54,0.00121,-6.71790);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,55,0.02516,-3.68231);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,56,0.00064,-7.35909);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,57,0.00080,-7.13342);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,58,0.02744,-3.59583);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,59,0.00103,-6.87651);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,60,0.01660,-4.09808);
INSERT INTO ExperimentalCodonsPoblations (idOrganism, idCodon, poblation, poblationLn) VALUES (5,61,0.01606,-4.13114);

--Ajusto la forma de buscar por indices
set optimizer_switch='mrr=on'
