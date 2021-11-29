export interface MentorInterface {
    viewsId: number,
    firstName: string,
    lastName: string, 
    email:string,
    status: string, 
    startDate: string,
    endDate: string, 
    phoneNumber: string, 
    ethnicity: string, 
    address: string, 
    role: string,
    sessionGroupId: number,
    sessionGroupName: string
}

export interface MentorSessionInterface {
    mentorId: number,
    sessionGroup: string;
    attendance: string;
    dateTime: string;
    duration: string;
    note: string;
}

export interface MentorQuestionnaireInterface {
    questionnaireName: string;
    date: string;
    questions: string[];
    answers: string[];
}

export const emptyMentor: MentorInterface = {
    viewsId: 0,
    firstName: "Loading...",
    lastName: "", 
    email: "",
    status: "",
    startDate: "",
    endDate: "", 
    phoneNumber: "", 
    ethnicity: "", 
    address: "", 
    role: "",
    sessionGroupId: -1,
    sessionGroupName: ""
}

export const emptySession: MentorSessionInterface = {
    mentorId: 0,
    sessionGroup: "Loading...",
    attendance: "",
    dateTime: "",
    duration: "",
    note: "",
};

export const emptyQuestionnaire: MentorQuestionnaireInterface = {
    questionnaireName: "Loading...",
    date: "",
    questions: [],
    answers: [],
}