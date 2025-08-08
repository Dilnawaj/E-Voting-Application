import axios from 'axios';

const BASE_URL =  process.env.REACT_APP_API+"election-commission";


export const registerAdmin = async (admin)=>{
  const response = await axios.post(BASE_URL,admin);
  return response.data;
}

export const getAdminByEmail = async (email)=>{
  const response = await axios.get(`${BASE_URL}?email=${email}`);
  return response.data;
}


export const getAllEvents = async ()=>{
  console.log("Fetching all events from:", BASE_URL+'/events');
  const response = await axios.get(`${BASE_URL}/events`);
  return response.data;
}



export const registerEvent= async (event,email)=>{
  const response = await axios.post(`${BASE_URL}/events?email=${email}`,event);
  return response.data;
}

export const getAllEventsByEmail = async (email)=>{
  console.log("Fetching all events from:", BASE_URL+'/events');
  const response = await axios.get(`${BASE_URL}/events/${email}`);
  return response.data;
}

