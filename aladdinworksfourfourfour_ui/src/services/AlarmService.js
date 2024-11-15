import http from "../http-common"; 

class AlarmService {
  getAllAlarms(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/alarm/alarms`, searchDTO);
  }

  get(alarmId) {
    return this.getRequest(`/alarm/${alarmId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/alarm?field=${matchData}`, null);
  }

  addAlarm(data) {
    return http.post("/alarm/addAlarm", data);
  }

  update(data) {
  	return http.post("/alarm/updateAlarm", data);
  }
  
  uploadImage(data,alarmId) {
  	return http.postForm("/alarm/uploadImage/"+alarmId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new AlarmService();
