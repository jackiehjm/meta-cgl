#!/usr/bin/python

import os, sys

# The value represents the total number of P! requirements available 
# inside the CGL 5.0 template
p1_reqs = 113
files_no = 0
cnt = 0
nr = 0

def compliance_calc(reqs_len):
	number_p1=0;
	for i in range(0,reqs_len):
			number_p1=number_p1+1
	procent=float(number_p1)/p1_reqs*100.
	print "CGL compliance: %.2f%%" % procent

build_dir = os.environ.get('BUILDDIR')
if not build_dir:
	print "Source the build environment before executing this script!"
	sys.exit(1)
director = build_dir + "/tmp/work"

def populate_compl(content, files):
	temp = content.split('\n')
	global cnt

	for j in range(0, len(temp[0].split(",")) ):
		ID[cnt] = temp[0].split(",")[j]
		P[cnt] = temp[1].split(",")[j]
		D[cnt] = temp[2].split(",")[j]

		if ID[cnt].split('.')[0] == "AVL":
			shadow[cnt]=1
		if ID[cnt].split('.')[0] == "CFH":
			shadow[cnt]=2
		if ID[cnt].split('.')[0] == "CSM":
			shadow[cnt]=3
		if ID[cnt].split('.')[0] == "CCM":
			shadow[cnt]=4
		if ID[cnt].split('.')[0] == "CAF":
			shadow[cnt]=5
		if ID[cnt].split('.')[0] == "CDIAG":
			shadow[cnt]=6
		if ID[cnt].split('.')[0] == "SMM":
			shadow[cnt]=7
		if ID[cnt].split('.')[0] == "SPM":
			shadow[cnt]=8
		if ID[cnt].split('.')[0] == "SFA":
			shadow[cnt]=9
		if ID[cnt].split('.')[0] == "PRF":
			shadow[cnt]=10
		if ID[cnt].split('.')[0] == "STD":
			shadow[cnt]=12
		if ID[cnt].split('.')[0] == "SEC":
			shadow[cnt]=13
		if ID[cnt].split('.')[0] == "PMS":
			shadow[cnt]=14		
		cnt=cnt+1

def sort_reqs():
	for i in range(0, nr-1):
		for j in range(i+1, nr):
			if shadow[i] > shadow[j]:
				aux=shadow[i]
				shadow[i]=shadow[j]
				shadow[j]=aux
				aux=ID[i]
				ID[i]=ID[j]
				ID[j]=aux
				aux=P[i]
				P[i]=P[j]
				P[j]=aux
				aux=D[i]
				D[i]=D[j]
				D[j]=aux;
			else:
				if shadow[i]==shadow[j]:
					if ID[i]>ID[j]:
						aux=shadow[i]
						shadow[i]=shadow[j]
						shadow[j]=aux
						aux=ID[i]
						ID[i]=ID[j]
						ID[j]=aux
						aux=P[i]
						P[i]=P[j]
						P[j]=aux
						aux=D[i]
						D[i]=D[j]
						D[j]=aux		

for root, dirs, files in os.walk(director):
    for file in files:
    	if  file == "calculator.txt" :
    		files_no = files_no + 1

    		f = open(os.path.join(root, file))
    		temp = f.read().split('\n')
    		nr=nr+len(temp[1].split(","))

ID = [None] * nr
P = [None] * nr
D = [None] * nr
shadow = [0] * nr

for root, dirs, files in os.walk(director):
    for file in files:
    	if  file == "calculator.txt" :
			f = open(os.path.join(root, file))
			content=f.read()
			populate_compl(content, files_no)
			f.close()

sort_reqs()
compliance_calc(nr)
