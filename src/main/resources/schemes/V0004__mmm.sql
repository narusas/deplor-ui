CREATE TABLE `accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `repository` int(11) DEFAULT NULL,
  `name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3280 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `branches` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `lastRevision` int(11) NOT NULL,
  `repository` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `changes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `revision` int(11) NOT NULL,
  `type` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `path` varchar(2048) COLLATE utf8_bin NOT NULL DEFAULT '',
  `resource` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178983 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `deploymentRequest` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `branch` int(11) DEFAULT NULL,
  `status` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `message` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `deploymentRequest_changes` (
  `deployId` int(11) unsigned NOT NULL,
  `changeId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`deployId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `deploySet` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `deploySet_requests` (
  `deploySetId` int(11) NOT NULL,
  `requestId` int(11) NOT NULL,
  PRIMARY KEY (`deploySetId`,`requestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `repository` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '',
  `name` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '',
  `headRevision` int(11) DEFAULT NULL,
  `workerId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `workerPassword` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `resources` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `repository` int(11) DEFAULT NULL,
  `path` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36658 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `revisions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `branch` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `message` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37963 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


