# 미니 프로젝트 - 야구 관리 프로그램

## DB

### 1) DB 테이블 및 더미데이터 작성

```sql
create
database baseball;
use
baseball;

CREATE TABLE stadium
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(10),
    created_at TIMESTAMP
)engine=innoDB default charset=utf8mb4;

create table team
(
    id         int primary key auto_increment,
    stadium_id int         default null,
    name       varchar(10) default null,
    created_at timestamp   default null,
    foreign KEY (stadium_id) REFERENCES stadium (id)
)engine=innoDB default charset=utf8mb4;

create table player
(
    id         int primary key auto_increment,
    team_id    int         default null,
    name       varchar(10) default null,
    position   varchar(10) default null,
    created_at timestamp   default null,
    foreign KEY (team_id) REFERENCES team (id),
    UNIQUE (team_id, position)
)engine=innoDB default charset=utf8mb4;

create table out_player
(
    id         int primary key auto_increment,
    player_id  int          default null,
    reason     varchar(100) default null,
    created_at timestamp,
    FOREIGN KEY (player_id) REFERENCES player (id)
)engine=innoDB default charset=utf8mb4;

-- stadium
insert into stadium(name, created_at)
values ('부산야구장', now());
insert into stadium(name, created_at)
values ('대구야구장', now());
insert into stadium(name, created_at)
values ('창원야구장', now());

-- team
insert into stadium(name, created_at)
values ('부산야구장', now());
insert into stadium(name, created_at)
values ('대구야구장', now());
insert into stadium(name, created_at)
values ('창원야구장', now());

-- player
insert into player(team_id, name, position, created_at)
values (1, '김원중', '투수', now());
insert into player(team_id, name, position, created_at)
values (1, '유강남', '포수', now());
insert into player(team_id, name, position, created_at)
values (1, '고승민', '1루수', now());
insert into player(team_id, name, position, created_at)
values (1, '박승욱', '2루수', now());
insert into player(team_id, name, position, created_at)
values (1, '한동희', '3루수', now());
insert into player(team_id, name, position, created_at)
values (1, '윤동희', '우익수', now());
insert into player(team_id, name, position, created_at)
values (1, '김민수', '중견수', now());
insert into player(team_id, name, position, created_at)
values (1, '황성빈', '좌익수', now());
insert into player(team_id, name, position, created_at)
values (1, '이학주', '유격수', now());

insert into player(team_id, name, position, created_at)
values (2, '뷰캐넌', '투수', now());
insert into player(team_id, name, position, created_at)
values (2, '강민호', '포수', now());
insert into player(team_id, name, position, created_at)
values (2, '류지혁', '1루수', now());
insert into player(team_id, name, position, created_at)
values (2, '김동진', '2루수', now());
insert into player(team_id, name, position, created_at)
values (2, '강한울', '3루수', now());
insert into player(team_id, name, position, created_at)
values (2, '이성규', '우익수', now());
insert into player(team_id, name, position, created_at)
values (2, '김성윤', '중견수', now());
insert into player(team_id, name, position, created_at)
values (2, '류승민', '좌익수', now());
insert into player(team_id, name, position, created_at)
values (2, '이재현', '유격수', now());

insert into player(team_id, name, position, created_at)
values (3, '이용찬', '투수', now());
insert into player(team_id, name, position, created_at)
values (3, '박세혁', '포수', now());
insert into player(team_id, name, position, created_at)
values (3, '김수윤', '1루수', now());
insert into player(team_id, name, position, created_at)
values (3, '박민우', '2루수', now());
insert into player(team_id, name, position, created_at)
values (3, '서호철', '3루수', now());
insert into player(team_id, name, position, created_at)
values (3, '손아섭', '우익수', now());
insert into player(team_id, name, position, created_at)
values (3, '천재환', '중견수', now());
insert into player(team_id, name, position, created_at)
values (3, '권희동', '좌익수', now());
insert into player(team_id, name, position, created_at)
values (3, '김주원', '유격수', now());
```

### 2) 각 기능에 대한 쿼리문

#### 1. stadium

```sql
-- insert
insert into stadium(name, created_at)
values (?, now());

-- update
update stadium
set name =?
where id = ?;

-- delete
delete
from stadium
where id = ?;

-- findByAll
select *
from stadium;

-- findById
select *
from stadium
where name = ?;

```

#### 2. team

```sql
-- insert
insert into team(stadium_id, name, created_at)
values (?, ?, now());

-- update
update team
set name = ?
where name = ?;

-- delete 
delete
from team
where name = ?;

-- findByAll
select *
from team;

-- findByStadiumId
select*
from team
where stadium_id = ?;

-- findByAllWithStadium
select s.id,
       s.name       as stadium_name,
       s.created_at as stadium_created_at,
       t.stadium_id,
       t.name       as team_name,
       t.created_at as team_created_at
from team as t
         right outer join stadium as s
                          on t.stadium_id = s.id
order by id asc;

```

#### 3. player

```sql

-- insert
insert into player(team_id, name, position, created_at)
values (?, ?, ?, now());

-- update
update player
set name = ?
where name = ?;

-- updateOut
update player
set team_id = null
where id = ?;

-- delete
delete
from player
where name = ?;

-- findByAll
select *
from player
order by id asc;

-- findByTeam
select id, name, position, created_at
from player
where team_id = ?
order by id asc;

-- findByName
select *
from player
where name = ?;

-- findByPositionPivot
select 포지션,
       max(case when tname = '롯데' then pname else null end) as '롯데', max(case when tname = '삼성' then pname else null end) as '삼성', max(case when tname = '엔씨' then pname else null end) as '엔씨'
from (select p.position as '포지션', t.name as tname,
             p.name as pname
      from player as p
               inner join
           team as t
           on p.team_id = t.id) as page
group by 포지션;
```

#### 4. out_player
```sql
-- insert
insert into out_player(player_id, reason, created_at) values (?, ?, now());
                                                      
-- update
update out_player set reason =? where player_id = ?;

-- delete
delete from out_player where player_id = ?;

-- findByAll
select * from out_player order by id asc;

-- findByPlayerId
select * from out_player where player_id = ?;

-- findByAllWithPlayer
select pl.id, pl.name, pl.position, opl.reason, opl.created_at
                from out_player as opl
                right outer join player as pl
                on opl.player_id = pl.id
                order by id asc;

```