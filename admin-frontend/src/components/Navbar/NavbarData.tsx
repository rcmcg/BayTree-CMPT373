import React from 'react';
import * as AiIcons from 'react-icons/ai';


export const SideNavbar = [
    {
        title: 'Home',
        path: '/',
        icon: <AiIcons.AiOutlineHome size = '28px'/>,
        cName: 'nav-text'
    },
    {
        title: 'Mentors',
        path: '/mentors',
        icon: <AiIcons.AiOutlineUser size = '28px'/>,
        cName: 'nav-text'
    },
    {
        title: 'Mentees',
        path: '/mentees',
        icon: <AiIcons.AiOutlineTeam size = '28px'/>,
        cName: 'nav-text'
    },
    {
        title: 'Add Mentor',
        path: '/add',
        icon: <AiIcons.AiOutlineUserAdd size = '28px'/>,
        cName: 'nav-text'
    },
    {
        title: 'Questionnaires',
        path: '/questionnaires',
        cName: 'nav-text'
    },
    {
        title: 'Settings',
        path: '/settings',
        icon: <AiIcons.AiOutlineSetting size = '28px'/>,
        cName: 'nav-text'
    },
    {
        title: 'Resources',
        path: '/resources',
        icon: <AiIcons.AiOutlineSetting size = '28px'/>,
        cName: 'nav-text'
    },
];