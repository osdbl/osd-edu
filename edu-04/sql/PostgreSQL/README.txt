# Create role and database:  
psql -U postgres -d postgres -f create-database.sql
# Create and populate tables (password = osd)
psql -U osd -d osd-edu -f create-insert-tables.sql