// @flow
import { Typography } from '@material-ui/core';
import { Resource } from './types';
import React, { FormEventHandler, useEffect, useState } from 'react';

export const Resources = () => {
    const [resources, setResources] = useState<Resource[] | null>(null)

    return (<>
    <Typography variant="h3">Resources</Typography>
    </>
    );
};