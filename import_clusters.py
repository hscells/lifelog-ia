#!/usr/bin/python

import json
import pg8000
import argparse

def import_json(json_file):

	print "Connecting..."
	json_data = json.load(open(json_file))
	conn = pg8000.connect(user="ielab", password="IR@qut", host="web474.webfaction.com", database="lifelog_ia")
	cursor = conn.cursor()

	# print "Rebuilding tables..."
	# sql = open("database.sql", "r").read()
	# cursor.executemany(sql, [])
	# conn.commit()

	print "Inserting data..."

	chunk_size = 10;
	current_chunk = 0;
	images_in_chunk = 0;
	cursor.execute('INSERT INTO chunks (annotated) VALUES (false)')

	for item in json_data:
		print str(images_in_chunk) + "/" + str(current_chunk)
		row = [ item["name"], item["data"], current_chunk]
		cursor.execute('INSERT INTO images (name, data, annotated, chunk_id) VALUES (%s, %s, false, %s)', row)
		images_in_chunk += 1
		if images_in_chunk == chunk_size:
			images_in_chunk = 0
			cursor.execute('INSERT INTO chunks (annotated) VALUES (false)')
			current_chunk += 1
	conn.commit()
	cursor.close()


if __name__ == '__main__':
	argparser = argparse.ArgumentParser(description='Import JSON into sqlite')
	argparser.add_argument('json_file', help='The JSON file to import.')
	args = argparser.parse_args()

	import_json(args.json_file)
