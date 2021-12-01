// @flow
import { Resource } from './types';
import { getResourcesList, createResource, deleteResource } from './resources.api';
import { Box, IconButton, makeStyles, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@material-ui/core/';
import React, { FormEventHandler, useEffect, useState } from 'react';

export const Resources = () => {
    const [resources, setResources] = useState<Resource[] | null>(null)

    return (<>
    <Typography variant="h3">Resources</Typography>
    <TableContainer>
  <Table>
    <TableHead>
      <TableRow>
        <TableCell>ID</TableCell>
        <TableCell>Resource Name</TableCell>
        <TableCell>Resource Link</TableCell>
      </TableRow>
    </TableHead>
    <TableBody>
      {resources && resources.map(resource => (
        <TableRow>
          <TableCell>
            {resource.resourceId}
          </TableCell>
          <TableCell>
            {resource.resourceName}
          </TableCell>
          <TableCell>
            {resource.resourceLink}
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  </Table>
  </TableContainer>
    </>
    );
};