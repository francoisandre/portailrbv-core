
DROP TABLE IF EXISTS `OBSERVATORY_CONTACT`;
DROP TABLE IF EXISTS `PERSON`;
DROP TABLE IF EXISTS `SITES`;
DROP TABLE IF EXISTS `DRAINAGE_BASIN`;
DROP TABLE IF EXISTS `OBSERVATORY`;

CREATE TABLE IF NOT EXISTS `OBSERVATORY` (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `SHORT_LABEL` varchar(40) NULL,
  `LONG_LABEL` varchar(256) NULL,
  `DESCRIPTION` text NULL,
  `USE_CONDITIONS` text NULL,
  `PUBLIC_ACCESS_LIMITATIONS` text NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `DRAINAGE_BASIN` (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `LABEL` varchar(40) NULL,
  `OBSERVATORY_ID` int(10) UNSIGNED NOT NULL,
  `NORTH_BOUND_LATITUDE` DOUBLE NULL,
  `SOUTH_BOUND_LATITUDE` DOUBLE NULL,
  `EAST_BOUND_LONGITUDE` DOUBLE NULL,
  `WEST_BOUND_LONGITUDE` DOUBLE NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT FOREIGN KEY (OBSERVATORY_ID) references OBSERVATORY(ID)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `SITE` (
  `ID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `LABEL` varchar(40) NULL,
  `LATITUDE` DOUBLE NULL,
  `LONGITUDE` DOUBLE NULL,
  `ALTITUDE` DOUBLE NULL,
  `DRAINAGE_BASIN_ID` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT FOREIGN KEY (DRAINAGE_BASIN_ID) references DRAINAGE_BASIN(ID)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;