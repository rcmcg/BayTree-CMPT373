import React from 'react';
import axios, {AxiosError, AxiosResponse} from "axios";

interface IState {
    mentorsList: [],
}

var attendedSessions = "";
var id;

interface props {
    mentorsList: [],
}

class SelectMentor extends React.Component<props> {
    render () {
        return (
            <div>
                <label form="selectMentorId"> Mentor Name </label>
                <select id={"selectMentorId"} name={"mentorId"}>
                    <option value={""}>Select a mentor</option>
                    {this.props.mentorsList.map(mentor => <option value = {mentor["viewsId"]}>{mentor["firstName"] + " " + mentor["lastName"]}</option>)}
                </select>
            </div>
        )
    }
}

const fetchSessions  = async () => {
    axios.get('http://localhost:8080//sessions/get/views/{id}')
        .then((res: any) => {
            if(res.date !== null) {
                attendedSessions = res.attendance;
            }
        })
        .catch((err: any) => {
            console.log(err);
        })
}

class SessionInfo {
    render() {
        return (
            <div> </div>
        )
    }
}

export class Home extends React.Component<{}, IState>  {

    componentDidMount() {
        axios.get('http://localhost:8080//user/get/mentors/all')
            .then((res: any) => {
                this.setState({ mentorsList : res.data });
                id = res.viewsId;
            })
            .catch((err: any) => {
                console.log(err);
            })
    }

    render() {
        return (
            <div className='home'>
                <h1>Home</h1> <br/>
                <SelectMentor mentorsList={this.state.mentorsList} /> <br/>
                <div>

                </div>
            </div>
        );
    }
}

export default Home;