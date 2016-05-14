
do_cgl_calc() {
	NO_COMPLY="NO"
	if [ -z "${CGL_ID}" ]; then
		echo "${NO_COMPLY}" > calculator.txt	
	else
		echo "${CGL_ID}" > calculator.txt
	fi
	if [ -z "${PRIORITY}" ]; then
		echo "${NO_COMPLY}" >> calculator.txt	
	else
		echo "${PRIORITY}" >> calculator.txt
	fi
	if [ -z "${DISCLOSURE}" ]; then
		echo "${NO_COMPLY}" >> calculator.txt	
	else
		echo "${DISCLOSURE}" >> calculator.txt
	fi
}

addtask cgl_calc after do_fetch before do_build
