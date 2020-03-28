# Creazione della network:
> docker network create --driver bridge odoo-network

# Creazione del volume per la conservazione del database
> docker volume create --name odoo-db-data

# Creazione del database odoo
> docker run -d --name odoo-db  --env POSTGRES_USER=odoo --env POSTGRES_PASSWORD=odoopwd --env POSTGRES_DB=postgres --network=odoo-network --mount source=odoo-db-data,target=/var/lib/postgresql/data library/postgres:11.1

# Creazione del volume per la conservazione dei dati dell’erp odoo
> docker volume create --name odoo-data
> docker volume create --name odoo-extra-addons

# Aggiornamento o ricostruzione del contenitore
# Molte volte si desidera aggiornare il contenitore Odoo con una versione più recente di Odoo. Questo può essere fatto con questi passaggi:
# •	Arrestare e rimuovere il vecchio contenitore Odoo.
# •	Estrarre la versione più recente dell'immagine Odoo.
# •	Ricrea il contenitore Odoo.

# Per esegui gli aggiornamenti effettuare i seguenti passaggi:
> docker stop myodoo
> docker rm myodoo
> docker pull odoo
> docker run -d --name myodoo --link odoo-db:db -p 8069:8069 --network odoo-network --mount source=odoo-data,target=/var/lib/odoo --mount source=odoo-extra-addons,target=/mnt/extra-addons --env POSTGRES_PASSWORD=odoopwd odoo/odoo:13.0

> docker start myodoo odoo-db
> docker stop myodoo odoo-db
