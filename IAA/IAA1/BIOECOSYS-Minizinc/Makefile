all: adj_area
D = $$0

create_dzn:
	python3 create_dzn.py > MODEL.dzn
	sed -i 's/(/''/g' MODEL.dzn
	sed -i 's/)/''/g' MODEL.dzn
	python3 create_dzn2.py > MODEL2.dzn
	
adj_area: symbolic
	cut -d, -f1 input/Adjacencias_VSousa2.csv > i.csv
	sed -i '1d' i.csv
	python3 convert_u.py > ii.csv
	cat ii.csv > iii.csv
	cut -d, -f2 input/Adjacencias_VSousa2.csv > i.csv
	sed -i '1d' i.csv
	python3 convert_u.py > ii.csv
	paste --delimiter='' iii.csv ii.csv > i.csv
	cut -d, -f6 input/Adjacencias_VSousa2.csv > iii.csv
	sed -i '1d' iii.csv
	paste --delimiter='' i.csv iii.csv > ii.csv
	python3 adjacent_area.py > i.csv
	sqlite3 database2.db < imp/import_adj_area.sql

symbolic: hascut
	cut -d, -f3,4,6,7,8 input/vsousa-prescr-1.csv > ii.csv
	sed -i '1d' ii.csv
	python3 convert_s.py > iii.csv
	paste --delimiter='' i.csv iii.csv > ii.csv
	sqlite3 database2.db < imp/import_symb.sql

hascut: action
	python3 convert_to_bool.py > ii.csv
	sqlite3 database2.db < imp/import_hascut.sql
	

action: adj
	cut -d, -f1 input/vsousa-prescr-1.csv > i.csv
	sed -i '1d' i.csv
	python3 convert_u.py > ii.csv
	cut -d, -f2 input/vsousa-prescr-1.csv > i.csv
	sed -i '1d' i.csv
	python3 convert_p.py > iii.csv
	paste --delimiter='' ii.csv iii.csv > i.csv
	cut -d, -f3,4,5,6,7,8 input/vsousa-prescr-1.csv > ii.csv
	sed -i '1d' ii.csv
	paste --delimiter='' i.csv ii.csv > iii.csv
	sqlite3 database2.db < imp/import_action.sql
	
	

adj: area
	cut -d, -f1 input/Adjacencias_VSousa2.csv > i.csv
	sed -i '1d' i.csv
	python3 convert_u.py > ii.csv
	cat ii.csv > iii.csv
	cut -d, -f2 input/Adjacencias_VSousa2.csv > i.csv
	sed -i '1d' i.csv
	python3 convert_u.py > ii.csv
	paste --delimiter='' iii.csv ii.csv > i.csv
	cut -d, -f4 input/Adjacencias_VSousa2.csv > iii.csv
	sed -i '1d' iii.csv
	paste --delimiter='' i.csv iii.csv > ii.csv
	sed -i '/-1/d' ii.csv
	sqlite3 database2.db < imp/import_adj.sql

area: perimeter
	cut -d, -f3 input/Adjacencias_VSousa.csv > i.csv
	sed -i '1d' i.csv
	sed -i '1d' i.csv
	paste --delimiter='' ii.csv i.csv > iii.csv
	sqlite3 database2.db < imp/import_area.sql

perimeter: presc_ids
	cut -d, -f1 input/Adjacencias_VSousa.csv > i.csv
	sed -i '1d' i.csv
	sed -i '1d' i.csv
	python3 convert_u.py > ii.csv
	cut -d, -f2 input/Adjacencias_VSousa.csv > i.csv
	sed -i '1d' i.csv
	sed -i '1d' i.csv
	paste --delimiter='' ii.csv i.csv > iii.csv
	sqlite3 database2.db < imp/import_per.sql


presc_ids: ug_ids 
	cut -d, -f2 input/vsousa-prescr-1.csv > i.csv
	sed -i '1d' i.csv
	awk '!seen[$(D)]++' i.csv > ii.csv
	sqlite3 database2.db < imp/import_presc_ids.sql

ug_ids: create_tables
	cut -d, -f1 input/Adjacencias_VSousa.csv > i.csv
	sed -i '1d' i.csv
	sed -i '1d' i.csv
	sqlite3 database2.db < imp/import_ug.sql	

create_tables:
	sqlite3 database2.db  < create.sql


clean:
	rm -rf *.db
	rm -rf i.csv
	rm -rf ii.csv
	rm -rf iii.csv
	rm -rf MODEL.dzn

