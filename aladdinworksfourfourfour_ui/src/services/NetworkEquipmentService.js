import http from "../http-common"; 

class NetworkEquipmentService {
  getAllNetworkEquipments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/networkEquipment/networkEquipments`, searchDTO);
  }

  get(networkEquipmentId) {
    return this.getRequest(`/networkEquipment/${networkEquipmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/networkEquipment?field=${matchData}`, null);
  }

  addNetworkEquipment(data) {
    return http.post("/networkEquipment/addNetworkEquipment", data);
  }

  update(data) {
  	return http.post("/networkEquipment/updateNetworkEquipment", data);
  }
  
  uploadImage(data,networkEquipmentId) {
  	return http.postForm("/networkEquipment/uploadImage/"+networkEquipmentId, data);
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

export default new NetworkEquipmentService();
