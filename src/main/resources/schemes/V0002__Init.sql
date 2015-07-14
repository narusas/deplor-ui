-- Create syntax for TABLE 'accounts'
CREATE TABLE `accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `repository` int(11) DEFAULT NULL,
  `name` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3368 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'branches'
CREATE TABLE `branches` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `lastRevision` int(11) NOT NULL,
  `repository` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'changes'
CREATE TABLE `changes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `revision` int(11) NOT NULL,
  `type` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '',
  `path` varchar(2048) COLLATE utf8_bin NOT NULL DEFAULT '',
  `resource` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=231678 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'deployedResource'
CREATE TABLE `deployedResource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `branch` int(11) DEFAULT NULL,
  `resource` int(11) DEFAULT NULL,
  `change_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23801 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'deploymentRequest'
CREATE TABLE `deploymentRequest` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `branch` int(11) DEFAULT NULL,
  `status` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `message` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'deploymentRequest_changes'
CREATE TABLE `deploymentRequest_changes` (
  `deployId` int(11) unsigned NOT NULL,
  `changeId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`deployId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'deploySet'
CREATE TABLE `deploySet` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `timestamp` timestamp NULL DEFAULT NULL,
  `status` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `previous` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'deploySet_requests'
CREATE TABLE `deploySet_requests` (
  `deploySetId` int(11) NOT NULL,
  `requestId` int(11) NOT NULL,
  PRIMARY KEY (`deploySetId`,`requestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'repository'
CREATE TABLE `repository` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '',
  `name` varchar(512) COLLATE utf8_bin NOT NULL DEFAULT '',
  `headRevision` int(11) DEFAULT NULL,
  `workerId` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `workerPassword` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'resources'
CREATE TABLE `resources` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `repository` int(11) DEFAULT NULL,
  `branch` int(11) DEFAULT NULL,
  `latestChange` int(11) DEFAULT NULL,
  `type` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `path` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63256 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'revisions'
CREATE TABLE `revisions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `branch` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `message` text COLLATE utf8_bin,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46020 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


-- Create syntax for TABLE 'stage'
CREATE TABLE `stage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `branch` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `previous` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'stage_deployResource'
CREATE TABLE `stage_deployResource` (
  `stage` int(11) unsigned NOT NULL,
  `deployResource` int(11) unsigned NOT NULL,
  PRIMARY KEY (`stage`,`deployResource`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'users'
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Create syntax for TABLE 'users_accounts'
CREATE TABLE `users_accounts` (
  `userId` int(11) unsigned NOT NULL,
  `accountId` int(11) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



INSERT INTO `repository` (`id`, `url`, `name`, `headRevision`, `workerId`, `workerPassword`)
VALUES
	(1, X'73766E3A2F2F3132342E3234332E39312E3232303A383838382F6568696D617274', X'6568696D617274', NULL, X'6A73616E36', X'6A73616E36'),
	(2, X'73766E3A2F2F3132342E3234332E39312E3232303A383838382F64657369676E', X'64657369676E', NULL, X'6A73616E36', X'6A73616E36');

	
	INSERT INTO `branches` (`id`, `name`, `lastRevision`, `repository`)
VALUES
	(7, X'32303135303431335F53686F706C696E6B6572417069', 0, 1),
	(8, X'7472756E6B', 0, 1),
	(9, X'4C45435332305F4C53425F414E414C59535953', 0, 1);

	
	INSERT INTO `stage` (`id`, `branch`, `name`, `previous`)
VALUES
	(1, 8, X'4C5342', NULL),
	(2, 8, X'444556', 1),
	(3, 8, X'535447', 1),
	(4, 8, X'505244', 1);
