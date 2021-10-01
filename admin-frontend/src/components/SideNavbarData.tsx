import React from 'react';
import * as AiIcons from 'react-icons/ai';


export const SideNavbar = [
    {
        title: 'Home',
        path: '/',
        icon: <AiIcons.AiFillHome/>,
        cName: 'nav-text'
    },
    {
        title: 'Mentors',
        path: '/mentors',
        icon: <AiIcons.AiOutlineUser/>,
        cName: 'nav-text'
    },
    {
        title: 'Mentees',
        path: '/mentees',
        icon: <AiIcons.AiOutlineTeam/>,
        cName: 'nav-text'
    },
    {
        title: 'Add Mentor',
        path: '/add',
        icon: <AiIcons.AiOutlineUserAdd/>,
        cName: 'nav-text'
    },
    {
        title: 'Settings',
        path: '/settings',
        icon: <AiIcons.AiFillSetting/>,
        cName: 'nav-text'
    },
];