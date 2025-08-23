
use `hb-03-one-to-many`;

create table review (
    id int NOT NULL AUTO_INCREMENT,
    title varchar(100) NOT NULL,
    content varchar(500) DEFAULT NULL,
    course_id int NOT NULL,

    PRIMARY KEY (id),

    KEY FK_COURSE_idx (course_id),

    CONSTRAINT FK_COURSE
    FOREIGN KEY (course_id)
    REFERENCES course (id)

    ON DELETE NO ACTION
    ON UPDATE NO ACTION

) Engine=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;