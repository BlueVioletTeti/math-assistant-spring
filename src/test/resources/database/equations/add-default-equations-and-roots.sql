insert into equation (id, equation) values (1, '2*x+5=17');
insert into equation (id, equation) values (2, '-1.3*5/x=1.2');
insert into equation (id, equation) values (3, '2*x*x=10');
insert into equation (id, equation) values (4, '2*(x+5+x)+5=10');
insert into equation (id, equation) values (5, '17=2*x+5');
insert into root (id, root) values (1, 6);
insert into root (id, root) values (2, -5.4166666666666666);
insert into root (id, root) values (3, -2.23606797749979);
insert into root (id, root) values (4, 2.23606797749979);
insert into root (id, root) values (5, -1.25);
insert into equation_root (equation_id, root_id) values (1, 1);
insert into equation_root (equation_id, root_id) values (2, 2);
insert into equation_root (equation_id, root_id) values (3, 3);
insert into equation_root (equation_id, root_id) values (3, 4);
insert into equation_root (equation_id, root_id) values (4, 5);
insert into equation_root (equation_id, root_id) values (5, 1);
