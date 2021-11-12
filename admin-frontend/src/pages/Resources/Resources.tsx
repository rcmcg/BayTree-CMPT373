import React, { useEffect, useState } from 'react';
import { getResourcesList } from './Resources.api';
import { Resource } from './types';
import { IconButton, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@material-ui/core/';
import {Delete} from '@material-ui/icons'
import { Table } from 'react-bootstrap';

// interface ResourcesProps {
//   Resource: any

// }

// const Resources: React.FC<ResourcesProps> = ({Resource}) => {

// }

function Resources() {
  const [resources, setResource] = useState<Resource[] | null>(null)
  useEffect(() => {
    const fetchResourceList = async () => {
      const resourceList = await getResourcesList()
      console.log(resourceList)
      setResource(resourceList)
    }
    fetchResourceList()
  },[])
  return (<>
  <Typography variant="h3">Resources</Typography>
  <TableContainer>
  <Table>
    <TableHead>
      <TableRow>
        <TableCell>ID</TableCell>
        <TableCell>Resource Name</TableCell>
        <TableCell>Resource Link</TableCell>
        <TableCell>Actions</TableCell>
      </TableRow>
    </TableHead>
    <TableBody>
      {resources && resources.map(resource => (
        <TableRow key={resource.id}>
          <TableCell>
            {resource.id}
          </TableCell>
          <TableCell>
            {resource.resourceName}
          </TableCell>
          <TableCell>
            {resource.resourceLink}
          </TableCell>
          <TableCell>
            <IconButton>
              <Delete />
            </IconButton>
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  </Table>
  </TableContainer>
  </>  
  );
}

export default Resources;