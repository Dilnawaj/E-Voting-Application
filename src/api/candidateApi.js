import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API +"candidate";

export const getAllCandidates =  async ()=>{
  const response = await axios.get(BASE_URL);
  return response.data;
}

export const registerCandidate = async (candidate)=>{
  const response = await axios.post(BASE_URL,candidate);
  return response.data;
}
export const getCandidateByEmail = async (email)=>{
  const response = await axios.get(`${BASE_URL}/email?email=${email}`);
  return response.data;
}

export const getLiveVotes = async (constituency) => {
  const response = await axios.get(`${BASE_URL}/live-votes/${constituency}`);
  return response.data;
}


export const getNotifications = async (email) => {
  const response = await axios.get(`${BASE_URL}/notification?email=${email}`);
  return response.data;
}
export const setNotificationStatus = async(NotificationId)=>{
  const response = await axios.put(`${BASE_URL}/notifications/${NotificationId}/read`);
  return response.data;
}